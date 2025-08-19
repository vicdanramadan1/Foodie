package org.betaapps.foodie.presentation.restaurants.restaurantslist

import GetRestaurantsUseCase
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.betaapps.foodie.domain.model.Response


class RestaurantsListScreenViewModel(private val getRestaurantsUseCase: GetRestaurantsUseCase) :
    ScreenModel {

    private val _restaurants = MutableStateFlow(RestaurantsScreenState())
    val restaurants = _restaurants

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        screenModelScope.launch {
            getRestaurantsUseCase.invoke().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _restaurants.value = _restaurants.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Response.Success -> {
                        _restaurants.value = _restaurants.value.copy(
                            restaurants = response.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        _restaurants.value = _restaurants.value.copy(
                            error = response.message,
                            isLoading = false
                        )
                    }
                }

            }
        }
    }
}