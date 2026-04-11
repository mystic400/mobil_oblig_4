package com.example.mobil_oblig_4

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Home til CustomerInfo
    @Test
    fun navigateFromHomeToCustomerInfo() {

        composeTestRule
            .onNodeWithTag("start_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("customer_screen")
            .assertIsDisplayed()
    }

    // til BikeSelection
    @Test
    fun navigateToBikeSelection() {

        composeTestRule.onNodeWithTag("start_button").performClick()
        composeTestRule.onNodeWithTag("next_button").performClick() // CustomerInfo → Category
        composeTestRule.onNodeWithTag("next_button").performClick() // Category → BikeSelection

        composeTestRule
            .onNodeWithTag("bike_screen")
            .assertIsDisplayed()
    }

    // til Payment
    @Test
    fun navigateFullFlowToPayment() {

        composeTestRule.onNodeWithTag("start_button").performClick()
        composeTestRule.onNodeWithTag("next_button").performClick()
        composeTestRule.onNodeWithTag("next_button").performClick()
        composeTestRule.onNodeWithTag("next_button").performClick() // → Summary

        composeTestRule.onNodeWithTag("pay_button").performClick()

        composeTestRule
            .onNodeWithText("Betaling")
            .assertExists()
    }
}