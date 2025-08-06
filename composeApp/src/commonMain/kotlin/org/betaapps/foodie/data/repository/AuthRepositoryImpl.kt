package org.betaapps.foodie.data.repository

import AuthRepository
import org.betaapps.foodie.data.model.User

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return if (email == "test@example.com" && password == "1234") {
            Result.success(User("1", "Test org.betaapps.foodie.data.model.User", email, ""))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Result<User> = Result.success(
        User("2", name, email ,"")
    )
    override suspend fun logout() {}
    override fun getCurrentUser(): User? = null
}
