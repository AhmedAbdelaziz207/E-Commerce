package com.example.domain.common

import com.example.domain.exception.ServerException
import kotlin.Exception


sealed class ResultWrapper<out T> {


    class Success<T>(val data: T) : ResultWrapper<T>()

    class Error<T>(val exception:Exception) : ResultWrapper<T>()

    class ServerError<T>(val exception: ServerException) : ResultWrapper<T>()

    object Loading : ResultWrapper<Nothing>()


}