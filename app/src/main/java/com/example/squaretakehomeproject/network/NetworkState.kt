package com.example.squaretakehomeproject.network

sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()

    data class Success<T>(val data: T) : NetworkState<T>()
    object EmptyResponse: NetworkState<Nothing>()
    object MalformedResponse: NetworkState<Nothing>()


    sealed class Error: NetworkState<Nothing>()
    data class NetworkUnavailableError(val message: String): Error()
    data class RequestTimeOutError(val message: String): Error()
    data class UnauthorizedAccess(val message: String): Error()
    data class ServerError(val message: String): Error()
    data class UnknownError(val message: String): Error()
}
