package org.betaapps.foodie.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Restaurant(
    val id: String,
    val name: String,
    val imageUrl: String,
    val dishes: List<Dish>,
    val rating: String,
    val deliveryTime: String,
    val address: String,
    val phoneNumber: String
)
