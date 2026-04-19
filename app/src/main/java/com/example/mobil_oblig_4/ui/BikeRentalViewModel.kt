package com.example.mobil_oblig_4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mobil_oblig_4.BikeApplication
import com.example.mobil_oblig_4.data.BikeRepository
import com.example.mobil_oblig_4.model.Bike
import com.example.mobil_oblig_4.model.BikeCategory
import com.example.mobil_oblig_4.model.BikeSize
import com.example.mobil_oblig_4.network.RetrofitInstance
import com.example.mobil_oblig_4.data.NetworkBikeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
//ViewModel får repository sendt inn som parameter.
//
//Den lager det ikke selv, og er derfor ikke hardkoblet.
class BikeRentalViewModel(private val repository: BikeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(BikeRentalUiState())
    val uiState: StateFlow<BikeRentalUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val selection = _uiState.value.customerSelection
                refreshDerivedState(selection)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun setAdult(isAdult: Boolean) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val current = _uiState.value.customerSelection
                val updatedSelection = if (isAdult) {
                    val hybridBikes = repository.getHybridBikes()
                    val hybridSizes = repository.getHybridBikeSizes()
                    current.copy(
                        isAdult = true,
                        height = 175,
                        category = BikeCategory.Hybrid,
                        bikeSize = hybridSizes.getOrNull(2) ?: BikeSize(),
                        bike = hybridBikes.firstOrNull() ?: current.bike
                    )
                } else {
                    val kidsBikes = repository.getKidsBikes()
                    val kidsSizes = repository.getKidsBikeSizes()
                    current.copy(
                        isAdult = false,
                        age = 6,
                        height = 120,
                        category = BikeCategory.KidsBike,
                        bikeSize = kidsSizes.getOrNull(2) ?: BikeSize(),
                        bike = kidsBikes.firstOrNull() ?: current.bike,
                        showBikesOfType = 0
                    )
                }
                refreshDerivedState(updatedSelection)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun setAge(ageText: String) {
        val age = ageText.toIntOrNull() ?: 6
        val updated = _uiState.value.customerSelection.copy(age = age.coerceAtLeast(1))
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setHeight(heightText: String) {
        val height =
            heightText.toIntOrNull() ?: if (_uiState.value.customerSelection.isAdult) 175 else 120
        val updated = _uiState.value.customerSelection.copy(height = height.coerceAtLeast(50))
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setNumberOfDays(daysText: String) {
        val days = daysText.toIntOrNull() ?: 1
        val updated = _uiState.value.customerSelection.copy(numberOfDays = days.coerceAtLeast(1))
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setStartDate(date: Date) {
        val updated = _uiState.value.customerSelection.copy(startDate = date)
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setCategory(category: BikeCategory) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val sizes = getBikeSizesForCategory(category)
                val allBikesInCategory = getBikesForCategory(category)
                val bikes = filterBikesByType(
                    bikes = allBikesInCategory,
                    filter = _uiState.value.customerSelection.showBikesOfType
                )

                val recommended = getRecommendedSize(
                    category = category,
                    height = _uiState.value.customerSelection.height,
                    age = _uiState.value.customerSelection.age,
                    isAdult = _uiState.value.customerSelection.isAdult
                )

                val updated = _uiState.value.customerSelection.copy(
                    category = category,
                    bikeSize = if (sizes.contains(recommended)) recommended else sizes.firstOrNull()
                        ?: BikeSize(),
                    bike = bikes.firstOrNull() ?: allBikesInCategory.firstOrNull()
                    ?: _uiState.value.customerSelection.bike
                )

                refreshDerivedState(updated)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun setBikeTypeFilter(filter: Int) {
        val updated = _uiState.value.customerSelection.copy(showBikesOfType = filter)
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setBikeSize(size: BikeSize) {
        val updated = _uiState.value.customerSelection.copy(bikeSize = size)
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun setBike(bike: Bike) {
        val updated = _uiState.value.customerSelection.copy(bike = bike)
        viewModelScope.launch { refreshDerivedState(updated) }
    }

    fun resetAll() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                refreshDerivedState(CustomerSelection())
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun refreshDerivedState(selection: CustomerSelection) {
        val categories = getCategoriesForCustomer(selection.isAdult)
        val sizes = getBikeSizesForCategory(selection.category)

        val recommended = getRecommendedSize(
            category = selection.category,
            height = selection.height,
            age = selection.age,
            isAdult = selection.isAdult
        )

        val filteredBikes = filterBikesByType(
            bikes = getBikesForCategory(selection.category),
            filter = selection.showBikesOfType
        )

        val finalBike = if (filteredBikes.any { it.id == selection.bike.id }) {
            filteredBikes.first { it.id == selection.bike.id }
        } else {
            filteredBikes.firstOrNull() ?: getBikesForCategory(selection.category).firstOrNull()
            ?: selection.bike
        }

        val finalSize =
            if (sizes.any { it.label == selection.bikeSize.label && it.wheelSize == selection.bikeSize.wheelSize }) {
                selection.bikeSize
            } else {
                recommended
            }

        val fixedSelection = selection.copy(
            bike = finalBike,
            bikeSize = finalSize
        )

        val totalPrice = PriceCalculator.calculateTotalPrice(
            pricePerDay = fixedSelection.bike.pricePerDay,
            numberOfDays = fixedSelection.numberOfDays
        )

        _uiState.update {
            it.copy(
                customerSelection = fixedSelection,
                currentCategories = categories,
                currentBikeSizes = sizes,
                currentBikes = filteredBikes,
                recommendedBikeSize = recommended,
                priceTotal = totalPrice
            )
        }
    }

    private fun getCategoriesForCustomer(isAdult: Boolean): List<BikeCategory> {
        return if (isAdult) {
            listOf(
                BikeCategory.Road,
                BikeCategory.Mountain,
                BikeCategory.Hybrid,
                BikeCategory.Gravel
            )
        } else {
            listOf(BikeCategory.KidsBike)
        }
    }

    private suspend fun getBikeSizesForCategory(category: BikeCategory): List<BikeSize> {
        return when (category) {
            BikeCategory.Road -> repository.getRoadBikeSizes()
            BikeCategory.Mountain -> repository.getMountainBikeSizes()
            BikeCategory.Hybrid -> repository.getHybridBikeSizes()
            BikeCategory.Gravel -> repository.getGravelBikeSizes()
            BikeCategory.KidsBike -> repository.getKidsBikeSizes()
        }
    }

    private suspend fun getBikesForCategory(category: BikeCategory): List<Bike> {
        return when (category) {
            BikeCategory.Road -> repository.getRoadBikes()
            BikeCategory.Mountain -> repository.getMountainBikes()
            BikeCategory.Hybrid -> repository.getHybridBikes()
            BikeCategory.Gravel -> repository.getGravelBikes()
            BikeCategory.KidsBike -> repository.getKidsBikes()
        }
    }

    private fun filterBikesByType(bikes: List<Bike>, filter: Int): List<Bike> {
        return when (filter) {
            1 -> bikes.filter { !it.electric }
            2 -> bikes.filter { it.electric }
            else -> bikes
        }
    }

    private suspend fun getRecommendedSize(
        category: BikeCategory,
        height: Int,
        age: Int,
        isAdult: Boolean
    ): BikeSize {
        val sizes = getBikeSizesForCategory(category)

        if (sizes.isEmpty()) return BikeSize()

        return if (!isAdult || category == BikeCategory.KidsBike) {
            sizes.firstOrNull {
                height in it.heightFrom..it.heightTo || age in it.ageFrom..it.ageTo
            } ?: sizes.lastOrNull() ?: BikeSize()
        } else {
            sizes.firstOrNull {
                height in it.heightFrom..it.heightTo
            } ?: sizes.lastOrNull() ?: BikeSize()
        }
    }

    companion object {
        fun provideFactory(
            application: BikeApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BikeRentalViewModel(
                    application.container.bikeRepository
                ) as T
            }
        }
    }
}
