package com.example.mobil_oblig_4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobil_oblig_4.R
import com.example.mobil_oblig_4.model.BikeCategory
import com.example.mobil_oblig_4.model.BikeSize
import com.example.mobil_oblig_4.ui.BikeRentalUiState
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SummaryScreen(
    uiState: BikeRentalUiState,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit,
    onPayClick: () -> Unit
) {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val selection = uiState.customerSelection

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(stringResource(R.string.summary_title))

        Text("${stringResource(R.string.bike_brand)} ${selection.bike.brand.name}")
        Text("${stringResource(R.string.bike_model)} ${selection.bike.brandModel}")
        Text("${stringResource(R.string.electric)} ${if (selection.bike.electric) "Ja" else "Nei"}")
        Text("${stringResource(R.string.size)} ${sizeText(selection.bikeSize, selection.category)}")
        Text("${stringResource(R.string.start_date_label)} ${formatter.format(selection.startDate)}")
        Text("${stringResource(R.string.days_label)} ${selection.numberOfDays}")
        Text(
            text = "${stringResource(R.string.total_price)} ${"%.2f".format(uiState.priceTotal)} kr",
            modifier = Modifier.testTag("summaryPrice")
        )

        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onHomeClick) {
                Text(stringResource(R.string.home))
            }
            Button(onClick = onBackClick) {
                Text(stringResource(R.string.back))
            }
            Button(
                onClick = onPayClick,
                modifier = Modifier.testTag("pay_button")
            ) {
                Text(stringResource(R.string.pay))
            }
        }
    }
}

fun sizeText(size: BikeSize, category: BikeCategory): String {
    return if (category == BikeCategory.KidsBike) {
        "Hjul ${size.wheelSize}\""
    } else {
        "${size.frameSizeFrom}-${size.frameSizeTo} cm (${size.label})"
    }
}