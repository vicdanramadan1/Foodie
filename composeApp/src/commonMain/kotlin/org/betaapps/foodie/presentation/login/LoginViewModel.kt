package org.betaapps.foodie.presentation.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.usecases.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ScreenModel {

    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState = _loginState

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.Login -> {
                login(email = event.email, password = event.password)
            }

            is LoginScreenEvent.DismissAlertDialog -> {
                dismissAlertDialog()
            }
        }
    }

    private fun login(email: String, password: String) {
        screenModelScope.launch {
            loginUseCase.invoke(email, password).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _loginState.value = _loginState.value.copy(
                            loading = true,
                            error = null,
                            firebaseUser = null
                        )
                    }

                    is Response.Success -> {
                        _loginState.value = _loginState.value.copy(
                            loading = false,
                            error = null,
                            firebaseUser = response.data.user
                        )

                    }

                    is Response.Error -> {
                        _loginState.value = _loginState.value.copy(
                            loading = false,
                            error = response.message,
                            firebaseUser = null
                        )
                    }
                }
            }
        }
    }

    private fun dismissAlertDialog() {
        _loginState.value = _loginState.value.copy(
            loading = false,
            firebaseUser = null,
            error = null
        )
    }
}