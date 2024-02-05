package com.example.data.model.wishlist

import com.example.domain.model.product.Product

data class WishlistResponse (
     val status : String ? = null ,
     val count : Int? = null ,
     val products:List<Product>
)