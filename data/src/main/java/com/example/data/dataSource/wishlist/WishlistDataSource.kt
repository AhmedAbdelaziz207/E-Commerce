package com.example.data.dataSource.wishlist

import com.example.data.model.wishlist.WishlistResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse

interface WishlistDataSource {
    suspend fun getWishlistUserWishlist(token:String):ResultWrapper<WishlistResponse>
    suspend fun removeProductFromWishlist(token: String,productId:String):ResultWrapper<BaseResponse>
}