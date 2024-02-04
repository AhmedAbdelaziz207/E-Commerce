package com.example.domain.repository.wishlist

import com.example.domain.model.baseResponse.BaseResponse
import com.example.data.model.wishlist.WishlistResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product

interface WishlistRepository {
    suspend fun getWishlistUserWishlist(token:String):ResultWrapper<WishlistResponse>
    suspend fun removeProductFromWishlist(token: String,productId:String):ResultWrapper<BaseResponse>
}