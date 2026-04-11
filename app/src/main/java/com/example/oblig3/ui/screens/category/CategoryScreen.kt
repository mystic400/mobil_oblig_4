package com.example.oblig3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oblig3.R
import com.example.oblig3.model.BikeCategory
import com.example.oblig3.ui.BikeRentalUiState
import com.example.oblig3.ui.BikeRentalViewModel
import com.example.oblig3.ui.components.BottomButtons

@Composable
fun CategoryScreen(
    uiState: BikeRentalUiState,
    viewModel: BikeRentalViewModel,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.choose_category),
            modifier = Modifier.testTag("categoryTitle")
        )

        uiState.currentCategories.forEach { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = uiState.customerSelection.category == category,
                        onClick = { viewModel.setCategory(category) }
                    )
                    .padding(vertical = 6.dp)
            ) {
                RadioButton(
                    selected = uiState.customerSelection.category == category,
                    onClick = { viewModel.setCategory(category) }
                )

                Icon(
                    painter = painterResource(id = categoryImageRes(category)),
                    contentDescription = categoryToText(category),
                    modifier = Modifier
                        .size(56.dp)
                        .padding(start = 8.dp, end = 8.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = categoryToText(category),
                    modifier = Modifier.padding(top = 16.dp)
                )
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

private fun categoryToText(category: BikeCategory): String {
    return when (category) {
        BikeCategory.Road -> "Road"
        BikeCategory.Mountain -> "Mountain"
        BikeCategory.Hybrid -> "Hybrid"
        BikeCategory.Gravel -> "Gravel"
        BikeCategory.KidsBike -> "KidsBike"
    }
}

private fun categoryImageRes(category: BikeCategory): Int {
    return when (category) {
        BikeCategory.Road -> com.example.oblig3.R.drawable.road_bike_24
        BikeCategory.Mountain -> com.example.oblig3.R.drawable.mountain_bike_24
        BikeCategory.Hybrid -> com.example.oblig3.R.drawable.hybrid_bike_24
        BikeCategory.Gravel -> com.example.oblig3.R.drawable.gravel_bike_24
        BikeCategory.KidsBike -> com.example.oblig3.R.drawable.kids_bike_24
    }
}
