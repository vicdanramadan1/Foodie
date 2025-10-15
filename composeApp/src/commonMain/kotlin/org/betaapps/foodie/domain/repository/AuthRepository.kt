package org.betaapps.foodie.domain.repository

import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Response<AuthResult>>
    suspend fun signup(user: User, password: String): Flow<Response<AuthResult>>
    suspend fun forgotPassword(email: String): Flow<Response<Unit>>
    suspend fun logout()
    suspend fun getCurrentUser(): FirebaseUser?
}