package org.betaapps.foodie.presentation.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.betaapps.foodie.domain.model.Input
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.usecases.ForgotPasswordUseCase
import org.betaapps.foodie.domain.usecases.LoginUseCase
import org.betaapps.foodie.domain.validation.EmailValidator
import org.betaapps.foodie.domain.validation.InputValidationResult
import org.betaapps.foodie.domain.validation.PasswordValidator

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ScreenModel {

    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState: StateFlow<LoginScreenState> = _loginState

    private val _email =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val email: StateFlow<Input> = _email

    private val _password =
        MutableStateFlow(Input(text = "", validationResult = InputValidationResult.Loading))
    val password: StateFlow<Input> = _password


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

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.Login -> {
                if (isFormValid()) {
                    login(email = event.email, password = event.password)
                } else {
                    _loginState.value = _loginState.value.copy(
                        alertDialogMessage = "please check all fields",
                        firebaseUser = null,
                        loading = false
                    )
                }
            }

            is LoginScreenEvent.ShowForgotPasswordDialog -> {
                _loginState.value = _loginState.value.copy(
                    showForgotPasswordDialog = true
                )
            }
            is LoginScreenEvent.SendPasswordByEmail -> {
                sendPasswordByEmail(event.email)
            }

            is LoginScreenEvent.DismissAlertDialog -> {
                dismissAlertDialog()
            }

            is LoginScreenEvent.EmailChanged -> {
                onEmailChanged(event.email)
            }

            is LoginScreenEvent.PasswordChanged -> {
                onPasswordChanged(event.password)
            }
        }
    }

    private fun isFormValid(): Boolean {
        return _email.value.validationResult is InputValidationResult.Success &&
                _password.value.validationResult is InputValidationResult.Success
    }

    private fun login(email: String, password: String) {
        screenModelScope.launch {
            loginUseCase.invoke(email, password).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _loginState.value = _loginState.value.copy(
                            loading = true,
                            alertDialogMessage = null,
                            firebaseUser = null,
                        )
                    }

                    is Response.Success -> {
                        _loginState.value = _loginState.value.copy(
                            loading = false,
                            alertDialogMessage = null,
                            firebaseUser = response.data.user
                        )

                    }

                    is Response.Error -> {
                        _loginState.value = _loginState.value.copy(
                            loading = false,
                            alertDialogMessage = response.message,
                            firebaseUser = null
                        )
                    }
                }
            }
        }
    }

    private fun sendPasswordByEmail(email: String)
    {
        screenModelScope.launch {
            forgotPasswordUseCase.invoke(email = email).collect { response ->
                when(response)
                {
                    is Response.Success -> {
                        _loginState.value = _loginState.value.copy(
                            alertDialogMessage = "password sent successfully"
                        )
                    }
                    is Response.Error -> {
                        _loginState.value = _loginState.value.copy(
                            alertDialogMessage = response.message
                        )
                    }
                    is Response.Loading -> {

                    }
                }

            }
        }
    }

    private fun dismissAlertDialog() {
        _loginState.value = _loginState.value.copy(
            loading = false,
            firebaseUser = null,
            alertDialogMessage = null,
            showForgotPasswordDialog = false
        )
    }
}