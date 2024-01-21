package com.example.data.model.product

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.product.Product
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductDto(

	@field:SerializedName("sold")
	val sold: Int? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Double? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<ProductSubcategory?>? = null,

	@field:SerializedName("category")
	val productCategory: ProductCategory? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("priceAfterDiscount")
	val priceAfterDiscount: Int? = null
) : Parcelable {


	fun toProduct(): Product {
		return Product(
			sold = this.sold,
			images = this.images,
			quantity = this.quantity,
			imageCover = this.imageCover,
			description = this.description,
			title = this.title,
			ratingsQuantity = this.ratingsQuantity,
			ratingsAverage = this.ratingsAverage,
			createdAt = this.createdAt,
			reviews = null,
			price = this.price,
			priceAfterDiscount = this.priceAfterDiscount,
			id = this.id,
			subcategory = this.subcategory?.map { it?.toSubCategory() },
			category = this.productCategory?.toProductCategory(),
			slug = this.slug,
			updatedAt = this.updatedAt
		)
	}
}