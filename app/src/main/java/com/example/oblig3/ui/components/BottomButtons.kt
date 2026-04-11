package com.example.oblig3.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtons(
    homeText: String,
    backText: String,
    nextText: String,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Button(
            onClick = onHomeClick,
            modifier = Modifier.testTag("home_button")
        ) {
            Text(homeText)
        }


        Button(
            onClick = onBackClick,
            modifier = Modifier.testTag("back_button")
        ) {
            Text(backText)
        }


        Button(
            onClick = onNextClick,
            modifier = Modifier.testTag("next_button")
        ) {
            Text(nextText)
        }
    }
}