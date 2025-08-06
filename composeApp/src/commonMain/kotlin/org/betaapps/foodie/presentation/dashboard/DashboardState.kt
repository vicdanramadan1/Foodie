package org.betaapps.foodie.presentation.dashboard

import org.betaapps.foodie.data.model.DishOrderItem
import org.betaapps.foodie.data.model.User

data class DashboardState (
    val dishOrderItems: List<DishOrderItem> = emptyList(),
    val user: User?
)