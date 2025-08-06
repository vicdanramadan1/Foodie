package org.betaapps.foodie.presentation.shoppingcart

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.betaapps.foodie.data.model.Dish
import org.betaapps.foodie.data.model.DishOrderItem
import org.betaapps.foodie.presentation.utils.AppLogger

class ShoppingCartViewModel : ScreenModel {

    private val _state = MutableStateFlow(ShoppingCartState())
    val state = _state

    fun onEvent(event: ShoppingCartEvent) {
        when (event) {
            is ShoppingCartEvent.AddItem -> {
                addItem(event.dish)
            }

            is ShoppingCartEvent.RemoveItem -> {
                removeItem(event.dish)
            }

            is ShoppingCartEvent.ClearOrder -> {
                clearOrder()
            }

            is ShoppingCartEvent.CalculateTotalPrice -> {
                getTotalPrice()
            }

        }
    }

    private fun addItem(dish: Dish) {
        val order = _state.value.order.toMutableList()
        val existingItem = order.find { it.item.id == dish.id }
        if (existingItem != null) {
            val updatedQuantity = existingItem.quantity + 1
            order[order.indexOf(existingItem)].quantity = updatedQuantity
            _state.value = _state.value.copy(
                order = order
            )
            AppLogger.i("order", _state.value.order.toString())
        } else {
            order.add(DishOrderItem(dish, 1))
            _state.value = _state.value.copy(
                order = order
            )
        }
    }

    private fun removeItem(dish: Dish) {
        val order = _state.value.order.toMutableList()
        val existingItem = order.find { it.item.id == dish.id }
        existingItem?.let {
            if (it.quantity > 1) {
                val updatedItem = it.copy(quantity = it.quantity - 1)
                order[order.indexOf(it)] = updatedItem
                _state.value = _state.value.copy(order = order)
                AppLogger.i("order", _state.value.order.toString())
            } else {
                order.remove(it)
                _state.value = _state.value.copy(order = order)
            }
        }
    }

    private fun clearOrder() {
    }

    private fun getTotalPrice() {
        _state.value = _state.value.copy(
            totalPrice = _state.value.order.sumOf { it.item.price.toDouble() * it.quantity }
        )
    }

}