package org.betaapps.foodie.presentation.login

import dev.gitlive.firebase.auth.FirebaseUser

data class LoginScreenState(
    val loading: Boolean = false,
    val firebaseUser: FirebaseUser? = null,
    val loginError: String? = null,
)
