package org.betaapps.foodie.domain.usecases

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.AuthRepository

class LoginUseCase(private val authRepo: AuthRepository) {
    suspend fun invoke(email: String, password: String): Flow<Response<AuthResult>> {
        return authRepo.login(email, password)
    }
}