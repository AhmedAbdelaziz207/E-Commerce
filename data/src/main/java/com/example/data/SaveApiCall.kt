package com.example.data

import com.example.data.model.baseResponse.BaseResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.exception.ServerException
import com.google.gson.Gson
import retrofit2.HttpException

suspend fun<T> safeApiCall(apiCall:suspend ()->T):ResultWrapper<T>{
   try {
       val data = apiCall.invoke()
       return ResultWrapper.Success(data)
   }catch (ex:Exception){
      when(ex){
         is ServerException->{
             return ResultWrapper.ServerError(ServerException(ex.message?:""))
         }
          is HttpException->{
              val errorBody = ex.response()?.errorBody().toString()
              val response = Gson().fromJson(errorBody,BaseResponse::class.java)
              return ResultWrapper.ServerError(ServerException(response.message.toString()))
          }
          else->{
              return ResultWrapper.Error(ex)

          }



      }

   }


}