package com.example.oblig3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oblig3.R

@Composable
fun HomeScreen(
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_title),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = onStartClick,
            modifier = Modifier.testTag("start_button")
                .width(220.dp)
                .testTag("startButton")
        ) {
            Text(stringResource(R.string.start_order))
        }

        Button(
            onClick = { },
            modifier = Modifier
                .width(220.dp)
                .padding(top = 12.dp)
        ) {
            Text(stringResource(R.string.exit))
        }
    }
}