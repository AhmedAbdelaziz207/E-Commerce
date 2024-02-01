package com.example.domain.model.cart.loggedCart

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoggedCartResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable