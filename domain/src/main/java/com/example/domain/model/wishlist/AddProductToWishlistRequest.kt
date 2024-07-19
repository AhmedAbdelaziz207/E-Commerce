package com.example.domain.model.wishlist

import com.google.gson.annotations.SerializedName

data class AddProductToWishlistRequest (
    @field:SerializedName("productId")
    val productId : String? = null
)