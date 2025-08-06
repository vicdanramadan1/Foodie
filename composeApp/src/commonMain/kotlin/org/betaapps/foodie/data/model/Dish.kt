package org.betaapps.foodie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val isAvailable: Boolean = true
)
