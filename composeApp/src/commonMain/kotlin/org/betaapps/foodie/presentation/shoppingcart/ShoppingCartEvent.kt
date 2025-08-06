package org.betaapps.foodie.presentation.shoppingcart

import org.betaapps.foodie.data.model.Dish

sealed class ShoppingCartEvent {

    class AddItem(val dish: Dish): ShoppingCartEvent()

    class RemoveItem(val dish: Dish): ShoppingCartEvent()

    data object ClearOrder: ShoppingCartEvent()

    data object CalculateTotalPrice: ShoppingCartEvent()

}