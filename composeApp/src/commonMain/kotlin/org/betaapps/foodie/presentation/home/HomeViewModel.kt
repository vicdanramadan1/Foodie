package org.betaapps.foodie.presentation.home

import GetRestaurantsUseCase
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.betaapps.foodie.data.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.betaapps.foodie.data.model.DishOrderItem


class HomeViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : ScreenModel {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    private val _dishOrderItems = MutableStateFlow<List<DishOrderItem>>(emptyList())
    val dishOrderItems: StateFlow<List<DishOrderItem>> = _dishOrderItems

   init {
        screenModelScope.launch {
            _restaurants.value = getRestaurantsUseCase.invoke()

        }
   }

    fun addToCart(item: DishOrderItem) {
        val current = _dishOrderItems.value.toMutableList()
        val existing = current.find { it.item.id == item.item.id }
        if (existing != null) {
            existing.quantity++
        } else {
            current.add(DishOrderItem(item.item, 1))
        }
        _dishOrderItems.value = current
    }

    fun removeFromCart(itemId: String) {
        _dishOrderItems.value = _dishOrderItems.value.filterNot { it.item.id == itemId }
    }

    fun clearCart() {
        _dishOrderItems.value = emptyList()
    }

}