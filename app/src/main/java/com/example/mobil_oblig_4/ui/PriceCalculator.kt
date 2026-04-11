package com.example.mobil_oblig_4.ui

object PriceCalculator {
    fun calculateTotalPrice(pricePerDay: Double, numberOfDays: Int): Double {
        return pricePerDay * numberOfDays
    }
}

fun calculatePrice(size: String, extras: Boolean): Int {
    val basePrice = when (size) {
        "S" -> 1000
        "M" -> 1500
        "L" -> 2000
        else -> 0
    }

    val extraPrice = if (extras) 500 else 0

    return basePrice + extraPrice
}