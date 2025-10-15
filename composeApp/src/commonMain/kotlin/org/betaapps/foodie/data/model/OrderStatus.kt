package org.betaapps.foodie.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class OrderStatus {
    PLACED,
    PREPARING,
    DELIVERING,
    DELIVERED,
    CANCELLED,
    BLANK
}