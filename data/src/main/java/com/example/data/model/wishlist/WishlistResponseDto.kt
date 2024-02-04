package com.example.data.model.wishlist

import android.os.Parcelable
import com.example.data.model.product.ProductDto
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WishlistResponseDto (
    @field:SerializedName("status")
    private val status : String ? = null ,
    @field:SerializedName("count")
    private val count : Int? = null ,
    @field:SerializedName("data")
    private val products:List<ProductDto>
) : Parcelable{
    fun toWishlistResponse():WishlistResponse{
        return WishlistResponse(status,count,products.map { it.toProduct() })
    }
}