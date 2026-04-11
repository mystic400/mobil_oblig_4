package com.example.mobil_oblig_4.model

import androidx.annotation.DrawableRes

data class Bike(
    val bikeCategory: BikeCategory,
    val electric: Boolean = false,
    val brand: Brand,
    val brandModel: String,
    val modelYear: Int,
    val pricePerDay: Double,
    @DrawableRes
    val imageResource: Int
)