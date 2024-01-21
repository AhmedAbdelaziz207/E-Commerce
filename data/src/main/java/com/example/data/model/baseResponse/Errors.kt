package com.example.data.model.baseResponse

import com.google.gson.annotations.SerializedName

data class Errors(
    @field:SerializedName("value")
    val value: String? = null,
    @field:SerializedName("msg")
    val msg: String? = null,
    @field:SerializedName("param")
    val param: String? = null,
    @field:SerializedName("location")
    val location: String? = null
)
