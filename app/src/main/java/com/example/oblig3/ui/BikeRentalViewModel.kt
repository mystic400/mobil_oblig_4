package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3.data.DataSource
import com.example.oblig3.model.Bike
import com.example.oblig3.model.BikeCategory
import com.example.oblig3.model.BikeSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class BikeRentalViewModel : ViewModel() {

    val dataSource: DataSource = DataSource()

    private val _uiState = MutableStateFlow(BikeRentalUiState())
    val uiState: StateFlow<BikeRentalUiState> = _uiState.asStateFlow()

    init {
        refreshDerivedState(_uiState.value.customerSelection)
    }

    fun setAdult(isAdult: Boolean) {
        val current = _uiState.value.customerSelection

        val updatedSelection = if (isAdult) {
            current.copy(
                isAdult = true,
                height = 175,
                category = BikeCategory.Hybrid,
                bikeSize = dataSource.hybridBikeSizes[2],
                bike = dataSource.hybridBikes[0]
            )
        } else {
            current.copy(
                isAdult = false,
                age = 6,
                height = 120,
                category = BikeCategory.KidsBike,
                bikeSize = dataSource.kidsBikeSizes[2],
                bike = dataSource.kidsBikes[0],
                showBikesOfType = 0
            )
        }

        refreshDerivedState(updatedSelection)
    }

    fun setAge(ageText: String) {
        val age = ageText.toIntOrNull() ?: 6
        val updated = _uiState.value.customerSelection.copy(age = age.coerceAtLeast(1))
        refreshDerivedState(updated)
    }

    fun setHeight(heightText: String) {
        val height = heightText.toIntOrNull() ?: if (_uiState.value.customerSelection.isAdult) 175 else 120
        val updated = _uiState.value.customerSelection.copy(height = height.coerceAtLeast(50))
        refreshDerivedState(updated)
    }

    fun setNumberOfDays(daysText: String) {
        val days = daysText.toIntOrNull() ?: 1
        val updated = _uiState.value.customerSelection.copy(numberOfDays = days.coerceAtLeast(1))
        refreshDerivedState(updated)
    }

    fun setStartDate(date: Date) {
        val updated = _uiState.value.customerSelection.copy(startDate = date)
        refreshDerivedState(updated)
    }

    fun setCategory(category: BikeCategory) {
        val sizes = getBikeSizesForCategory(category)
        val bikes = filterBikesByType(
            bikes = getBikesForCategory(category),
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
            bikeSize = if (sizes.contains(recommended)) recommended else sizes.firstOrNull() ?: BikeSize(),
            bike = bikes.firstOrNull() ?: getBikesForCategory(category).first()
        )

        refreshDerivedState(updated)
    }

    fun setBikeTypeFilter(filter: Int) {
        val updated = _uiState.value.customerSelection.copy(showBikesOfType = filter)
        refreshDerivedState(updated)
    }

    fun setBikeSize(size: BikeSize) {
        val updated = _uiState.value.customerSelection.copy(bikeSize = size)
        refreshDerivedState(updated)
    }

    fun setBike(bike: Bike) {
        val updated = _uiState.value.customerSelection.copy(bike = bike)
        refreshDerivedState(updated)
    }

    fun resetAll() {
        refreshDerivedState(CustomerSelection())
    }

    private fun refreshDerivedState(selection: CustomerSelection) {
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

        val finalBike = if (filteredBikes.contains(selection.bike)) {
            selection.bike
        } else {
            filteredBikes.firstOrNull() ?: getBikesForCategory(selection.category).first()
        }

        val finalSize = if (sizes.contains(selection.bikeSize)) {
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

        _uiState.value = BikeRentalUiState(
            customerSelection = fixedSelection,
            currentCategories = categories,
            currentBikeSizes = sizes,
            currentBikes = filteredBikes,
            recommendedBikeSize = recommended,
            priceTotal = totalPrice
        )
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

    private fun getBikeSizesForCategory(category: BikeCategory): List<BikeSize> {
        return when (category) {
            BikeCategory.Road -> dataSource.roadBikeSizes
            BikeCategory.Mountain -> dataSource.mountainBikeSizes
            BikeCategory.Hybrid -> dataSource.hybridBikeSizes
            BikeCategory.Gravel -> dataSource.gravelBikeSizes
            BikeCategory.KidsBike -> dataSource.kidsBikeSizes
        }
    }

    private fun getBikesForCategory(category: BikeCategory): List<Bike> {
        return when (category) {
            BikeCategory.Road -> dataSource.roadBikes
            BikeCategory.Mountain -> dataSource.mountainBikes
            BikeCategory.Hybrid -> dataSource.hybridBikes
            BikeCategory.Gravel -> dataSource.gravelBikes
            BikeCategory.KidsBike -> dataSource.kidsBikes
        }
    }

    private fun filterBikesByType(bikes: List<Bike>, filter: Int): List<Bike> {
        return when (filter) {
            1 -> bikes.filter { !it.electric }
            2 -> bikes.filter { it.electric }
            else -> bikes
        }
    }

    private fun getRecommendedSize(
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
            } ?: sizes.last()
        } else {
            sizes.firstOrNull {
                height in it.heightFrom..it.heightTo
            } ?: sizes.last()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BikeRentalViewModel() as T
            }
        }
    }
}