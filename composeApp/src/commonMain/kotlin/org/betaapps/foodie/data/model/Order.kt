package org.betaapps.foodie.data.model

data class Order(
    val id: String,
    val userId: String,
    val restaurantId: String,
    val items: List<DishOrderItem>,
    val total: Double,
    val status: OrderStatus = OrderStatus.BLANK,
)