package org.betaapps.foodie.data.repository

import RestaurantRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.betaapps.foodie.data.model.Restaurant
import org.betaapps.foodie.domain.model.Response

class RestaurantRepositoryImpl(private val fireStore: FirebaseFirestore) : RestaurantRepository {

    override suspend fun getRestaurants(): Flow<Response<List<Restaurant>>> {
        return flow {
            emit(Response.Loading)
            try {
                fireStore
                    .collection(collectionPath = "restaurants")
                    .snapshots
                    .collect { querySnapshot ->
                        val data = querySnapshot.documents.map { documentSnapshot ->
                            documentSnapshot.data<Restaurant>()
                        }
                        emit(Response.Success(data))
                    }
            } catch (e: Exception) {
                emit(Response.Error("Failed to fetch restaurants", e))
            }
        }
    }
}