package org.betaapps.foodie.presentation.login


sealed class LoginScreenEvent {
    data class EmailChanged(val email: String): LoginScreenEvent()
    data class PasswordChanged(val password: String): LoginScreenEvent()
    data class Login(val email: String , val password: String): LoginScreenEvent()
    data object ShowForgotPasswordDialog: LoginScreenEvent()
    data class SendPasswordByEmail(val email: String): LoginScreenEvent()
    data object DismissAlertDialog: LoginScreenEvent() }