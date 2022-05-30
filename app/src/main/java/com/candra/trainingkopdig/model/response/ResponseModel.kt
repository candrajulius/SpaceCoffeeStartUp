package com.candra.trainingkopdig.model.response

sealed class ResponseModel<T>{
    data class Success<T>(val data: T): ResponseModel<T>()
    data class Error<T>(val errorMessage: String): ResponseModel<T>()
    class isLoading<T> : ResponseModel<T>()

    companion object{
        fun <T> loading() = isLoading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(errorMessage: String) = Error<T>(errorMessage)
    }
}