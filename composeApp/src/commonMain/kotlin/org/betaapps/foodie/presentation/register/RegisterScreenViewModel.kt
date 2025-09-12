package org.betaapps.foodie.presentation.register

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Input
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.usecases.RegisterUserUseCase
import org.betaapps.foodie.domain.validation.AddressValidator
import org.betaapps.foodie.domain.validation.EmailValidator
import org.betaapps.foodie.domain.validation.FullNameValidator
import org.betaapps.foodie.domain.validation.InputValidationResult
import org.betaapps.foodie.domain.validation.PasswordValidator
import org.betaapps.foodie.domain.validation.PhoneNumberValidator

class RegisterScreenViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ScreenModel {

    private val _registrationState = MutableStateFlow(RegisterScreenState())
    val registrationState: StateFlow<RegisterScreenState> = _registrationState

    private val _email =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val email: StateFlow<Input> = _email

    private val _password =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val password: StateFlow<Input> = _password

    private val _fullName =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val fullName: StateFlow<Input> = _fullName

    private val _phoneNumber =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val phoneNumber: StateFlow<Input> = _phoneNumber

    private val _address =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val address: StateFlow<Input> = _address


    private fun onEmailChanged(email: String) {
        _email.value = _email.value.copy(
            text = email,
            validationResult = EmailValidator.validate(email)
        )
    }

    private fun onPasswordChanged(password: String) {
        _password.value = _password.value.copy(
            text = password,
            validationResult = PasswordValidator.validate(password)
        )
    }

    private fun onFullNameChanged(fullName: String) {
        _fullName.value = _fullName.value.copy(
            text = fullName,
            validationResult = FullNameValidator.validate(fullName)
        )
    }

    private fun onPhoneNumberChanged(phoneNumber: String) {
        _phoneNumber.value = _phoneNumber.value.copy(
            text = phoneNumber,
            validationResult = PhoneNumberValidator.validate(phoneNumber)
        )
    }

    private fun onAddressChanged(address: String) {
        _address.value = _address.value.copy(
            text = address,
            validationResult = AddressValidator.validate(address)
        )
    }

    private fun dismissAlertDialog() {
        _registrationState.value = _registrationState.value.copy(
            loading = false,
            firebaseUser = null,
            registrationError = null
        )
    }

    ///TODO : refactor this function
    private fun isFormValid(): Boolean {
        return _email.value.validationResult is InputValidationResult.Success
                && _password.value.validationResult is InputValidationResult.Success
                && _fullName.value.validationResult is InputValidationResult.Success
                && _phoneNumber.value.validationResult is InputValidationResult.Success
                && _address.value.validationResult is InputValidationResult.Success
    }

    private fun register(password: String) {
        screenModelScope.launch {
            registerUserUseCase.invoke(
                user = User(
                    address = _address.value.text,
                    email = _email.value.text,
                    order = null,
                    id = "",
                    name = _fullName.value.text
                ),

                password = password
            )
                .collect { response ->
                    when (response) {
                        is Response.Loading -> {
                            _registrationState.value = _registrationState.value.copy(
                                loading = true,
                                firebaseUser = null,
                                registrationError = null
                            )
                        }

                        is Response.Success -> {
                            _registrationState.value = _registrationState.value.copy(
                                loading = false,
                                firebaseUser = response.data.user,
                                registrationError = null
                            )
                        }

                        is Response.Error -> {
                            _registrationState.value = _registrationState.value.copy(
                                loading = false,
                                firebaseUser = null,
                                registrationError = response.message
                            )
                        }
                    }
                }
        }
    }

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.EmailChanged -> {
                onEmailChanged(email = event.email)
            }

            is RegisterScreenEvent.PasswordChanged -> {
                onPasswordChanged(password = event.password)
            }

            is RegisterScreenEvent.FullNameChanged -> {
                onFullNameChanged(fullName = event.fullName)
            }

            is RegisterScreenEvent.PhoneNumberChanged -> {
                onPhoneNumberChanged(phoneNumber = event.phoneNumber)
            }

            is RegisterScreenEvent.AddressChanged -> {
                onAddressChanged(address = event.address)
            }

            is RegisterScreenEvent.Register -> {
                if (isFormValid()) {
                    register(event.password)
                } else {
                    _registrationState.value = _registrationState.value.copy(
                        loading = false,
                        firebaseUser = null,
                        registrationError = "please check all fields"
                    )
                }

            }

            is RegisterScreenEvent.DismissAlertDialog -> {
                dismissAlertDialog()
            }
        }
    }
}