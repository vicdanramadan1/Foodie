package org.betaapps.foodie.presentation.register

import dev.gitlive.firebase.auth.FirebaseUser

data class RegisterScreenState(
    val loading: Boolean = false,
    val firebaseUser: FirebaseUser? = null,
    val registrationError: String? = null,
)