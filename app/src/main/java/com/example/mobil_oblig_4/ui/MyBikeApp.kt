package com.example.mobil_oblig_4.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobil_oblig_4.R
import com.example.mobil_oblig_4.ui.screens.BikeSelectionScreen
import com.example.mobil_oblig_4.ui.screens.CategoryScreen
import com.example.mobil_oblig_4.ui.screens.CustomerInfoScreen
import com.example.mobil_oblig_4.ui.screens.HomeScreen
import com.example.mobil_oblig_4.ui.screens.PaymentScreen
import com.example.mobil_oblig_4.ui.screens.SummaryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBikeApp(
    viewModel: BikeRentalViewModel = viewModel(factory = BikeRentalViewModel.Factory)
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = stringResource(R.string.app_icon_description),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(40.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Text(text = stringResource(R.string.app_name))
                    }
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(
                    onStartClick = {
                        navController.navigate(AppScreen.CustomerInfo.name)
                    }
                )
            }

            composable(route = AppScreen.CustomerInfo.name) {
                CustomerInfoScreen(
                    uiState = uiState,
                    viewModel = viewModel,
                    onHomeClick = {
                        navController.navigate(AppScreen.Home.name)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNextClick = {
                        navController.navigate(AppScreen.Category.name)
                    }
                )
            }

            composable(route = AppScreen.Category.name) {
                CategoryScreen(
                    uiState = uiState,
                    viewModel = viewModel,
                    onHomeClick = {
                        navController.navigate(AppScreen.Home.name)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNextClick = {
                        navController.navigate(AppScreen.BikeSelection.name)
                    }
                )
            }

            composable(route = AppScreen.BikeSelection.name) {
                BikeSelectionScreen(
                    uiState = uiState,
                    viewModel = viewModel,
                    onHomeClick = {
                        navController.navigate(AppScreen.Home.name)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNextClick = {
                        navController.navigate(AppScreen.Summary.name)
                    }
                )
            }

            composable(route = AppScreen.Summary.name) {
                SummaryScreen(
                    uiState = uiState,
                    onHomeClick = {
                        navController.navigate(AppScreen.Home.name)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPayClick = {
                        navController.navigate(AppScreen.Payment.name)
                    }
                )
            }

            composable(route = AppScreen.Payment.name) {
                PaymentScreen(
                    uiState = uiState,
                    viewModel = viewModel,
                    onHomeAfterPayment = {
                        navController.navigate(AppScreen.Home.name)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onHomeClick = {
                        navController.navigate(AppScreen.Home.name)
                    }
                )
            }
        }
    }
}
