package org.betaapps.foodie.domain.model

sealed class Response<out T> {

    data class Success<T>(val data: T) : Response<T>()

    data class Error(val message: String, val cause: Throwable? = null) : Response<Nothing>()

    data object Loading : Response<Nothing>()
}