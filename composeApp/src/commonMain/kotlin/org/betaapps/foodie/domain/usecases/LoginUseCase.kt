import org.betaapps.foodie.data.model.User

class LoginUseCase(private val authRepo: AuthRepository) {
    suspend fun invoke(email: String, password: String): Result<User> {
        return authRepo.login(email, password)
    }
}