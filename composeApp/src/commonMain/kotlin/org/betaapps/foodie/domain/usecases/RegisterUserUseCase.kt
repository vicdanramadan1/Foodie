package org.betaapps.foodie.domain.usecases

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(user: User, password: String): Flow<Response<AuthResult>> =
        authRepository.signup(
            user = user, password = password,
        )
}