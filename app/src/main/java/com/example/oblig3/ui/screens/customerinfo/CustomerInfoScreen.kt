package com.example.oblig3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oblig3.R
import com.example.oblig3.ui.BikeRentalUiState
import com.example.oblig3.ui.BikeRentalViewModel
import com.example.oblig3.ui.components.BottomButtons
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerInfoScreen(
    uiState: BikeRentalUiState,
    viewModel: BikeRentalViewModel,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = uiState.customerSelection.startDate.time
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            viewModel.setStartDate(Date(it))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(android.R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .testTag("customer_screen"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.customer_info_title))

        OutlinedTextField(
            value = dateFormatter.format(uiState.customerSelection.startDate),
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.start_date)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { showDatePicker = true }) {
            Text(stringResource(R.string.pick_date))
        }

        OutlinedTextField(
            value = uiState.customerSelection.numberOfDays.toString(),
            onValueChange = { viewModel.setNumberOfDays(it.filter { ch -> ch.isDigit() }) },
            label = { Text(stringResource(R.string.number_of_days)) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("daysField")
        )

        Text(stringResource(R.string.customer_type))

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = uiState.customerSelection.isAdult,
                        onClick = { viewModel.setAdult(true) }
                    )
            ) {
                RadioButton(
                    selected = uiState.customerSelection.isAdult,
                    onClick = { viewModel.setAdult(true) }
                )
                Text(
                    text = stringResource(R.string.adult),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = !uiState.customerSelection.isAdult,
                        onClick = { viewModel.setAdult(false) }
                    )
                    .testTag("childOption")
            ) {
                RadioButton(
                    selected = !uiState.customerSelection.isAdult,
                    onClick = { viewModel.setAdult(false) }
                )
                Text(
                    text = stringResource(R.string.child),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        if (!uiState.customerSelection.isAdult) {
            OutlinedTextField(
                value = uiState.customerSelection.age.toString(),
                onValueChange = { viewModel.setAge(it.filter { ch -> ch.isDigit() }) },
                label = { Text(stringResource(R.string.age_in_years)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ageField")
            )
        }

        OutlinedTextField(
            value = uiState.customerSelection.height.toString(),
            onValueChange = { viewModel.setHeight(it.filter { ch -> ch.isDigit() }) },
            label = { Text(stringResource(R.string.height_in_cm)) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("heightField")
        )

        BottomButtons(
            homeText = stringResource(R.string.home),
            backText = stringResource(R.string.back),
            nextText = stringResource(R.string.next),
            onHomeClick = onHomeClick,
            onBackClick = onBackClick,
            onNextClick = onNextClick
        )
    }
}
