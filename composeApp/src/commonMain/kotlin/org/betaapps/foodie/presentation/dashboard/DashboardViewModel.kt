package org.betaapps.foodie.presentation.dashboard

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.betaapps.foodie.data.model.DishOrderItem
import org.betaapps.foodie.data.model.Dish
import org.betaapps.foodie.data.model.Order
import org.betaapps.foodie.data.model.OrderStatus
import org.betaapps.foodie.data.model.User

class DashboardViewModel: ScreenModel {

    private val _dashboardState = MutableStateFlow(DashboardState(
       user = User(
           id = "123",
           name = "name",
           email = "email",
           order = Order(
               id = "123/1",
               userId = "123",
               total = 24.0,
               items = listOf(
                   DishOrderItem(
                       item = Dish(
                           "1",
                           "Pepperoni",
                           description = "pepperoni pepperoni",
                           "12.99",
                           "https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.16.png?alt=media&token=3034ac32-f692-46f2-bc02-001bc068e9c3",
                       ),
                       quantity = 3
                   )
               ),
               restaurantId = "",
               status = OrderStatus.BLANK,
           ),
           address = "",
           favoriteRestaurants = emptyList()
       )
    ))
    val dashboardState = _dashboardState

    init {
        _dashboardState.value = _dashboardState.value.user?.order?.items?.let {
            _dashboardState.value.copy(
                dishOrderItems = it
            )
        }!!
    }
    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnIncrease -> {
                addToCart(event.dishOrderItem)
            }

            is DashboardEvent.OnDecrease -> {
decrease(event.dishOrderItem)
            }
        }
    }

    private fun addToCart(item: DishOrderItem) {
        val current = _dashboardState.value.dishOrderItems.toMutableList()
        val existing = current.find { it.item.id == item.item.id }
        if (existing != null) {
            existing.quantity++
        } else {
            current.add(DishOrderItem(item.item, 1))
        }
        _dashboardState.value = _dashboardState.value.copy(
            user = _dashboardState.value.user?.copy(order = _dashboardState.value.user?.order?.copy(
                items = current.toList()
            ))
        )
        
    }

    private fun decrease(item: DishOrderItem) {
        val current = _dashboardState.value.dishOrderItems.toMutableList()
        val existing = current.find { it.item.id == item.item.id }
        if (existing != null) {
            existing.quantity--
        }
        _dashboardState.value = _dashboardState.value.copy(dishOrderItems = current)
    }
}