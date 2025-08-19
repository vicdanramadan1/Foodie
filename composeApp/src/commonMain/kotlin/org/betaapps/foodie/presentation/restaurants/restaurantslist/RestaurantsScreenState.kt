package org.betaapps.foodie.presentation.restaurants.restaurantslist

import org.betaapps.foodie.data.model.Restaurant

data class RestaurantsScreenState(
    val isLoading: Boolean = false,
    val restaurants: List<Restaurant> = emptyList(),
    val error: String? = null
)