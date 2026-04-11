package com.example.mobil_oblig_4.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobil_oblig_4.R
import com.example.mobil_oblig_4.ui.BikeRentalUiState
import com.example.mobil_oblig_4.ui.BikeRentalViewModel

@Composable
fun PaymentScreen(
    uiState: BikeRentalUiState,
    viewModel: BikeRentalViewModel,
    onHomeAfterPayment: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.payment_title))

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    context.getString(R.string.payment_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()

                viewModel.resetAll()
                onHomeAfterPayment()
            },
            modifier = Modifier.testTag("payNowButton")
        ) {
            Text("${stringResource(R.string.pay_now)} ${"%.2f".format(uiState.priceTotal)}")
        }

        Button(onClick = onBackClick) {
            Text(stringResource(R.string.back))
        }

        Button(onClick = onHomeClick) {
            Text(stringResource(R.string.home))
        }
    }
}