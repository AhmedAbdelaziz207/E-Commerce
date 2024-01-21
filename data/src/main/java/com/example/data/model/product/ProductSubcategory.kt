package com.example.data.model.product

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.data.dataSource.product.ProductsDataSource
import com.example.domain.model.product.ProductSubCategory
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductSubcategory(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable{
	fun toSubCategory():ProductSubCategory{
		return ProductSubCategory(name,id,category,slug)
	}
}