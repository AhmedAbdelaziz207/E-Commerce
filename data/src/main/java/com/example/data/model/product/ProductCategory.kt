package com.example.data.model.product

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.product.ProductCategory
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductCategory(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable{
	fun toProductCategory():ProductCategory{
		return ProductCategory(image,name,id, slug)
	}
}