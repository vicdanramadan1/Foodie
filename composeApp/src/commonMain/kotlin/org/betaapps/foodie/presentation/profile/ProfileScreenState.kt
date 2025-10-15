package org.betaapps.foodie.presentation.profile

import org.betaapps.foodie.data.model.User

data class ProfileScreenState(
    val loading: Boolean = false ,
    val user: User? = null,
    val error: String? = null
)