package org.betaapps.foodie.presentation.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import org.betaapps.foodie.presentation.restaurants.restaurantslist.RestaurantListScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        Navigator(
            RestaurantListScreen(
        )
        )
    }
}
