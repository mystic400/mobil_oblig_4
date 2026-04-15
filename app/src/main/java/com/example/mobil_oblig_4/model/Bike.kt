package com.example.mobil_oblig_4.model

import androidx.annotation.DrawableRes

data class Bike(
    val id: String,
    val bikeCategory: BikeCategory,
    val electric: Boolean = false,
    val brandId: String,
    val brand: Brand = Brand("", "", ""),
    val brandModel: String,
    val modelYear: Int,
    val pricePerDay: Double,
    @DrawableRes
    val imageResource: Int
)