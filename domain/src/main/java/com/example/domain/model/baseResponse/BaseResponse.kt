package com.example.domain.model.baseResponse

open class BaseResponse(
    val statusMsg:String?=null,
    val message:String? = null,
    val error: Error? = null
)
