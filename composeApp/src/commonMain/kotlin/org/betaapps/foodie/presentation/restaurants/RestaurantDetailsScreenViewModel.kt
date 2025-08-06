package org.betaapps.foodie.presentation.restaurants

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.betaapps.foodie.data.model.DishOrderItem

class RestaurantDetailsScreenViewModel: ScreenModel{

    private val _dishOrderItems = MutableStateFlow<List<DishOrderItem>>(emptyList())
    val dishOrderItems: StateFlow<List<DishOrderItem>> = _dishOrderItems

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

}