package org.betaapps.foodie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DishOrderItem(
    val item: Dish,
    var quantity: Int
)