package org.betaapps.foodie.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.AuthRepository
import org.betaapps.foodie.domain.repository.ProfileRepository
import org.betaapps.foodie.presentation.utils.AppLogger

class GetUserUseCase(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
)  {
    suspend fun invoke(): Flow<Response<User>>
    {
        val x: Flow<Response<User>>
         authRepository.getCurrentUser()
            .apply {
                AppLogger.i("usecase" , this?.uid?: "null")
                x = profileRepository.getUserInfo(this)
            }
        return x
    }
}