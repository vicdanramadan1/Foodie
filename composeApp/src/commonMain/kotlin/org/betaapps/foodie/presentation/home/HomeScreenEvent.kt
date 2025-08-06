package org.betaapps.foodie.presentation.home

import org.betaapps.foodie.data.model.DishOrderItem
import org.betaapps.foodie.data.model.Order

sealed class HomeScreenEvent {

    data class AddItemToCart(val item: DishOrderItem): HomeScreenEvent()

    data class RemoveItemFromCart(val item: DishOrderItem): HomeScreenEvent()

    data object ClearCart:  HomeScreenEvent()

    data class PlaceOrder(val order: Order): HomeScreenEvent()

}