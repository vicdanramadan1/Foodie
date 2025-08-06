package org.betaapps.foodie.presentation.shoppingcart

import org.betaapps.foodie.data.model.DishOrderItem

data class ShoppingCartState(
    val order: List<DishOrderItem> = emptyList(),
    val itemsNumber: Int = 0,
    val totalPrice: Double = 0.0
)
