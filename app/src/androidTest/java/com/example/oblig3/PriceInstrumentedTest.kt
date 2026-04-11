package com.example.oblig3

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.oblig3.ui.calculatePrice
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PriceInstrumentedTest {

    @Test
    fun testPriceSmallNoExtras() {
        val result = calculatePrice("S", false)
        assertEquals(1000, result)
    }

    @Test
    fun testPriceMediumWithExtras() {
        val result = calculatePrice("M", true)
        assertEquals(2000, result)
    }

    @Test
    fun testPriceLargeNoExtras() {
        val result = calculatePrice("L", false)
        assertEquals(2000, result)
    }
}