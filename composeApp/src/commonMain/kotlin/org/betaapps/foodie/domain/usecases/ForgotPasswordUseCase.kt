package org.betaapps.foodie.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.AuthRepository

class ForgotPasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun invoke(email: String): Flow<Response<Unit>> {
        return authRepository.forgotPassword(email)
    }
}