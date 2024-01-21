package com.example.domain.model.product

import android.os.Parcel
import android.os.Parcelable

data class Product(
	val sold: Int? = null,
	val images: List<String?>? = null,
	val quantity: Int? = null,
	val imageCover: String? = null,
	val description: String? = null,
	val title: String? = null,
	val ratingsQuantity: Int? = null,
	val ratingsAverage: Any? = null,
	val createdAt: String? = null,
	val reviews: List<Any?>? = null,
	val price: Int? = null,
	val priceAfterDiscount : Int? = null,
	val id: String? = null,
	val subcategory: List<ProductSubCategory?>? = null,
	val category: ProductCategory? = null,
	val slug: String? = null,
	val updatedAt: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.createStringArrayList(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		TODO("ratingsAverage"),
		parcel.readString(),
		TODO("reviews"),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		TODO("subcategory"),
		TODO("category"),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(sold)
		parcel.writeStringList(images)
		parcel.writeValue(quantity)
		parcel.writeString(imageCover)
		parcel.writeString(description)
		parcel.writeString(title)
		parcel.writeValue(ratingsQuantity)
		parcel.writeString(createdAt)
		parcel.writeValue(price)
		parcel.writeValue(priceAfterDiscount)
		parcel.writeString(id)
		parcel.writeString(slug)
		parcel.writeString(updatedAt)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Product> {
		override fun createFromParcel(parcel: Parcel): Product {
			return Product(parcel)
		}

		override fun newArray(size: Int): Array<Product?> {
			return arrayOfNulls(size)
		}
	}
}
