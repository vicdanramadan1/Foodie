package org.betaapps.foodie.data.repository

import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
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
    private val firebaseFirestore: FirebaseFirestore,
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

    override suspend fun forgotPassword(email: String): Flow<Response<Unit>> {
        return flow {
            emit(Response.Loading)
            try {
                val data = scope.async {
                    firebaseAuth.sendPasswordResetEmail(email = email)
                }.await()
                emit(Response.Success(data))

            }catch (e: Exception)
            {
                emit(Response.Error(e.message?: "something went wrong"))
            }
        }
    }
    override suspend fun signup(user: User, password: String): Flow<Response<AuthResult>> {
        return flow {
            emit(Response.Loading)
            try {
                val data =
                    scope.async {
                        firebaseAuth.createUserWithEmailAndPassword(
                            email = user.email,
                            password = password
                        )
                    }.await()
                        .apply {
                            firebaseFirestore.collection("users")
                                .add(user.copy(id = this.user?.uid!!))
                        }
                emit(Response.Success(data))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "something went wrong."))
            }

        }
    }

    override suspend fun logout() {}
    override fun getCurrentUser(): User? = null
}
