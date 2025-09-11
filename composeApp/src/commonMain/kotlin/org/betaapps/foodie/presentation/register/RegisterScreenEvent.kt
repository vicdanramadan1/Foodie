package org.betaapps.foodie.presentation.register

sealed class RegisterScreenEvent {

    data class EmailChanged(val email: String) : RegisterScreenEvent()
    data class PasswordChanged(val password: String) : RegisterScreenEvent()
    data class FullNameChanged(val fullName: String) : RegisterScreenEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : RegisterScreenEvent()
    data class AddressChanged(val address: String) : RegisterScreenEvent()
    data class Register(val email: String, val password: String) : RegisterScreenEvent()
    data object DismissAlertDialog : RegisterScreenEvent()
}