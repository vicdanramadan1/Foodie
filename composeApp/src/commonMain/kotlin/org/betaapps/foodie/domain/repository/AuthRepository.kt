import org.betaapps.foodie.data.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun signup(name: String, email: String, password: String): Result<User>
    suspend fun logout()
    fun getCurrentUser(): User?
}