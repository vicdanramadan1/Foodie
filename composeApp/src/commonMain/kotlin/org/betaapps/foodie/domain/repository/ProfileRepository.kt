package org.betaapps.foodie.domain.repository

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response

interface ProfileRepository {
    suspend fun getUserInfo(user: FirebaseUser?) : Flow<Response<User>>
    suspend fun updateUserInfo()
}