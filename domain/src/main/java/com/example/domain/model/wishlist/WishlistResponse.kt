package com.example.data.model.wishlist

import com.example.domain.model.product.Product

data class WishlistResponse (
    private val status : String ? = null ,
    private val count : Int? = null ,
    private val products:List<Product>
)