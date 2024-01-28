package com.example.data.model.cart

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.cart.addToCart.CartProduct
import com.google.gson.annotations.SerializedName

@Parcelize
data class CartProductDto(

	@field:SerializedName("product")
	val product: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
	,
	@field:SerializedName("imageCover")
	val imageCover : String ? = null

) : Parcelable{
	fun toCartProduct(): CartProduct {
		return CartProduct(
			product, price, count, id
		)
	}
}