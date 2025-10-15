package org.betaapps.foodie.presentation.profile

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.usecases.GetUserUseCase

class ProfileScreenViewModel(
    private val getUserUseCase: GetUserUseCase
) : ScreenModel {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state

    init {
        getUser()
    }

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.EditUserInfo -> {

            }

            is ProfileScreenEvent.DismissAlertDialog -> {
                dismissAlertDialog()
            }
        }
    }

    private fun getUser() {
        screenModelScope.launch {
            getUserUseCase.invoke().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true,
                            user = null,
                            error = null
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            user = response.data,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            user = null,
                            error = response.message
                        )
                    }
                }
            }
        }
    }

    private fun dismissAlertDialog() {
        _state.value = _state.value.copy(
            loading = false,
            error = null,
            user = null
        )
    }
}