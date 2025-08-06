package org.betaapps.foodie.presentation.home

import GetRestaurantsUseCase
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.serialization.json.Json
import org.betaapps.foodie.data.repository.RestaurantRepositoryImpl
import org.betaapps.foodie.presentation.restaurants.RestaurantListScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel{ HomeViewModel(GetRestaurantsUseCase(
            RestaurantRepositoryImpl()
        )) }

        val restaurants = viewModel.restaurants.collectAsState().value
        val jsonString = Json.encodeToString(restaurants)
        Navigator(
            RestaurantListScreen(
            jsonString,
        ))
    }
}
