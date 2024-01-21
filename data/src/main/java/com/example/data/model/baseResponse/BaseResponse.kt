package com.example.data.model.baseResponse

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @field:SerializedName("statusMsg")
    val statusMsg:String?=null,
    @field:SerializedName("message")
    val message:String? = null,
    @field:SerializedName("errors")
    val errors: Errors? = null
)
