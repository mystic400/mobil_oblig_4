package com.example.mobil_oblig_4.ui

import com.example.mobil_oblig_4.model.Bike
import com.example.mobil_oblig_4.model.BikeCategory
import com.example.mobil_oblig_4.model.BikeSize
import com.example.mobil_oblig_4.model.Brand
import java.util.Date

/**
 * UiState for sykkelutleie-skjermbildene. Inneholder alle valg som brukeren gjør i de ulike skjermbildene,
 * samt temporære lister og verdier som endres etter hvert som brukeren gjør valg i de ulike skjermbildene.
 * Brukes som stateflow-objekt i BikeRentalViewModel.
 */
data class BikeRentalUiState(
    // Valgene som brukeren gjør i de ulike skjermbildene:
    val customerSelection: CustomerSelection = CustomerSelection(),
    // Temporære lister som endres etter hvert som brukeren gjør valg i de ulike skjermbildene:
    val currentCategories: List<BikeCategory> = emptyList(),
    val currentBikeSizes: List<BikeSize> = emptyList(),
    val currentBikes: List<Bike> = emptyList(),
    // Anbefalt størrelse endres etter hvert som brukeren gjør valg i de ulike skjermbildene:
    val recommendedBikeSize: BikeSize = BikeSize(),
    // Prisen endres etter hvert som brukeren gjør valg i de ulike skjermbildene:
    val priceTotal: Double = 0.0,
    val isLoading: Boolean = false
)

/**
 * Hjelpeklasse til BikeRentalUiState som inneholder alle valg som brukeren gjør i de ulike skjermbildene.
 */
data class CustomerSelection(
    val isAdult: Boolean = true,
    val age: Int = 6,
    val height: Int = 175,
    val category: BikeCategory = BikeCategory.Hybrid,
    val bikeSize: BikeSize = BikeSize(),
    val startDate: Date = Date(),
    val numberOfDays: Int = 1,
    val showBikesOfType: Int = 0,    //0=Alle, 1=Vanlig, 2=Elektrisk
    val bike: Bike = Bike(
        id = "",
        bikeCategory = BikeCategory.Hybrid,
        brandId = "",
        brand = Brand("", "", ""),
        brandModel = "",
        modelYear = 0,
        pricePerDay = 0.0,
        imageResource = 0
    )
)
