package org.betaapps.foodie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val favoriteRestaurants: List<String> = emptyList(),
    val order: Order? = null
)