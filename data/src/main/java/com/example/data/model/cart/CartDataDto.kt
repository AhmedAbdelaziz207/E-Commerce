package com.example.data.model.cart

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.cart.addToCart.Data
import com.google.gson.annotations.SerializedName

@Parcelize
data class CartDataDto(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<CartProductDto?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable{
	fun toData(): Data {
		return Data(
			cartOwner, createdAt, totalCartPrice, v, id,
			products?.map {
						 it?.toCartProduct()
			},
			updatedAt
		)
	}

}