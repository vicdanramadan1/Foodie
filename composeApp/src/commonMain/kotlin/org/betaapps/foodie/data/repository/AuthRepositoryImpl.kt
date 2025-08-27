package org.betaapps.foodie.data.repository

import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Response<AuthResult>> {
        return flow {
            emit(Response.Loading)
            try {
                val data =
                    scope.async {
                        firebaseAuth.signInWithEmailAndPassword(email = email, password = password)
                    }.await()
                emit(Response.Success(data))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "something went wrong."))
            }
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Result<User> =
        Result.success(
            User("2", name, email, "")
        )

    override suspend fun logout() {}
    override fun getCurrentUser(): User? = null
}
