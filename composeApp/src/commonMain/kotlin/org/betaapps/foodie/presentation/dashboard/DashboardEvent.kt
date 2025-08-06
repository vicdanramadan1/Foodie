package org.betaapps.foodie.presentation.dashboard

import org.betaapps.foodie.data.model.DishOrderItem

sealed class DashboardEvent {
    data class OnIncrease(val dishOrderItem: DishOrderItem): DashboardEvent()
    data class OnDecrease(val dishOrderItem: DishOrderItem): DashboardEvent()
}