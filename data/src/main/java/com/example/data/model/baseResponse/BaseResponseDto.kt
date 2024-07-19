package com.example.data.model.baseResponse

import com.example.domain.model.baseResponse.BaseResponse
import com.google.gson.annotations.SerializedName

open class BaseResponseDto(
    @field:SerializedName("statusMsg")
    val statusMsg:String?=null,
    @field:SerializedName("message")
    val message:String? = null,
    @field:SerializedName("error")
    val errors: ErrorDto? = null
){
    fun toBaseResponse():BaseResponse{
        return BaseResponse(
            statusMsg, message, errors?.toError()
        )
    }
}
