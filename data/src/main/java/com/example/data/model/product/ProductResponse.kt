package com.example.data.model.product

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("data")
	val data: List<ProductDto?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
) : Parcelable