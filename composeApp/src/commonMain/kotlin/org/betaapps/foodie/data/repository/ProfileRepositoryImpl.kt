package org.betaapps.foodie.data.repository

import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.domain.model.Response
import org.betaapps.foodie.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : ProfileRepository {
    override suspend fun getUserInfo(user: FirebaseUser?): Flow<Response<User>> {
        val uid = user?.uid
        if (uid == null)
            return flow { Response.Error("not logged in") }
        else {
            return flow {
                emit(Response.Loading)
                try {
                    firestore.collection("users")
                        .document(uid)
                        .snapshots
                        .collect{ it->
                           val data = it.data<User>()
                            emit(Response.Success(data))
                        }

                } catch (e: Exception) {

                    emit(Response.Error(e.message ?: "something went wrong"))
                }
            }
        }
    }

    override suspend fun updateUserInfo() {
    }
}