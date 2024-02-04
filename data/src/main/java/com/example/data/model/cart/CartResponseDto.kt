package com.example.data.model.cart

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.cart.addToCart.CartResponse
import com.google.gson.annotations.SerializedName

@Parcelize
data class CartResponseDto(

	@field:SerializedName("data")
	val data: CartDataDto? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null
) : Parcelable{
	fun toCartResponse(): CartResponse {
		return CartResponse(
			data?.toData(),numOfCartItems
		)
	}
}