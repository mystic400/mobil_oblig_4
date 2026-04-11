package com.example.mobil_oblig_4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobil_oblig_4.R
import com.example.mobil_oblig_4.model.Bike
import com.example.mobil_oblig_4.model.BikeCategory
import com.example.mobil_oblig_4.model.BikeSize
import com.example.mobil_oblig_4.ui.BikeRentalUiState
import com.example.mobil_oblig_4.ui.BikeRentalViewModel
import com.example.mobil_oblig_4.ui.components.BottomButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeSelectionScreen(
    uiState: BikeRentalUiState,
    viewModel: BikeRentalViewModel,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .testTag("bike_screen"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            text = "${categoryToText(uiState.customerSelection.category)}-sykler",
            modifier = Modifier.testTag("bikeSelectionTitle")
        )

        Text(stringResource(R.string.choose_type_model_size))

        FilterRadioRow(
            selected = uiState.customerSelection.showBikesOfType,
            onSelected = { viewModel.setBikeTypeFilter(it) }
        )

        uiState.currentBikes.forEach { bike ->
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = uiState.customerSelection.bike == bike,
                        onClick = { viewModel.setBike(bike) }
                    )
            ) {
                RadioButton(
                    selected = uiState.customerSelection.bike == bike,
                    onClick = { viewModel.setBike(bike) }
                )
                Text(
                    text = bikeText(bike),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Text(stringResource(R.string.recommended_size))

        Text(
            text = sizeText(uiState.recommendedBikeSize, uiState.customerSelection.category),
            modifier = Modifier.testTag("recommendedSizeText")
        )


        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .menuAnchor()
                    .testTag("sizeDropdownButton")
            ) {
                Text(
                    sizeText(
                        uiState.customerSelection.bikeSize,
                        uiState.customerSelection.category
                    )
                )
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                uiState.currentBikeSizes.forEach { size ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                sizeText(
                                    size,
                                    uiState.customerSelection.category
                                )
                            )
                        },
                        onClick = {
                            viewModel.setBikeSize(size)
                            expanded = false
                        }
                    )
                }
            }
        }

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

@Composable
private fun FilterRadioRow(
    selected: Int,
    onSelected: (Int) -> Unit
) {
    Column {

        FilterOption(
            text = stringResource(R.string.filter_all),
            selected = selected == 0,
            onClick = { onSelected(0) },
            modifier = Modifier.testTag("filter_all")
        )

        FilterOption(
            text = stringResource(R.string.filter_non_electric),
            selected = selected == 1,
            onClick = { onSelected(1) },
            modifier = Modifier.testTag("filter_non_electric")
        )

        FilterOption(
            text = stringResource(R.string.filter_electric),
            selected = selected == 2,
            onClick = { onSelected(2) },
            modifier = Modifier.testTag("filter_electric")
        )
    }
}

@Composable
private fun FilterOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(selected = selected, onClick = onClick)
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, modifier = Modifier.padding(top = 12.dp))
    }
}

private fun bikeText(bike: Bike): String {
    return "${bike.brand.name}, ${bike.brandModel} (${bike.brand.country})"
}

@Composable
private fun sizeText(size: BikeSize, category: BikeCategory): String {
    return if (category == BikeCategory.KidsBike) {
        if (size.wheelSize == 0) {
            stringResource(R.string.no_size_recommendation)
        } else {
            stringResource(
                R.string.wheel_size_format,
                size.wheelSize,
                size.ageFrom,
                size.ageTo,
                size.heightFrom,
                size.heightTo
            )
        }
    } else {
        if (size.label == "--") {
            stringResource(R.string.no_size_recommendation)
        } else {
            stringResource(
                R.string.frame_size_format,
                size.frameSizeFrom,
                size.frameSizeTo,
                size.label
            )
        }
    }
}

@Composable
private fun categoryToText(category: BikeCategory): String {
    return when (category) {
        BikeCategory.Road -> stringResource(R.string.category_road)
        BikeCategory.Mountain -> stringResource(R.string.category_mountain)
        BikeCategory.Hybrid -> stringResource(R.string.category_hybrid)
        BikeCategory.Gravel -> stringResource(R.string.category_gravel)
        BikeCategory.KidsBike -> stringResource(R.string.category_kids)
    }
}