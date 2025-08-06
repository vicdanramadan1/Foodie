package org.betaapps.foodie.presentation.login

import LoginUseCase
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.betaapps.foodie.data.model.User

class LoginViewModel(private val loginUseCase: LoginUseCase): ScreenModel {
    private val _loginState = MutableStateFlow<Result<User>?>(null)
    val loginState: StateFlow<Result<User>?> = _loginState

    suspend fun login(email: String, password: String) {
        _loginState.value = loginUseCase.invoke(email, password)
    }
}