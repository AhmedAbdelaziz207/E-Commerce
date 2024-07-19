package com.example.domain.model.cart.loggedCart

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.product.Product
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductsItem(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
) : Parcelable