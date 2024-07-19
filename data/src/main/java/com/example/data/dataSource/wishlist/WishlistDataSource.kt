package com.example.data.dataSource.wishlist

import com.example.data.model.wishlist.WishlistResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse
import com.example.domain.model.wishlist.AddProductToWishlistRequest

interface WishlistDataSource {
    suspend fun addProductToWishlist(token: String,request: AddProductToWishlistRequest):ResultWrapper<BaseResponse>

    suspend fun getWishlistUserWishlist(token:String):ResultWrapper<WishlistResponse>
    suspend fun removeProductFromWishlist(token: String,productId:String):ResultWrapper<BaseResponse>
}