package org.betaapps.foodie.domain.repository

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Response<AuthResult>>
    suspend fun signup(name: String, email: String, password: String): Result<User>
    suspend fun logout()
    fun getCurrentUser(): User?
}