package com.example.data.dataSourceImp.wishlist

import com.example.data.api.WebServices
import com.example.data.dataSource.wishlist.WishlistDataSource
import com.example.data.model.wishlist.WishlistResponse
import com.example.data.safeApiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse
import com.example.domain.model.wishlist.AddProductToWishlistRequest
import javax.inject.Inject

class WishlistDataSourceImp@Inject constructor(
    private val webServices: WebServices
): WishlistDataSource {
    override suspend fun getWishlistUserWishlist(token:String): ResultWrapper<WishlistResponse> {
        return  safeApiCall {  webServices.getLoggedUserWishlist(token).toWishlistResponse()}
    }

    override suspend fun removeProductFromWishlist(
        token: String,
        productId: String
    ): ResultWrapper<BaseResponse> {
        return safeApiCall {   webServices.deleteProductFromWishlist(token,productId).toBaseResponse()}
    }

    override suspend fun addProductToWishlist(
        token: String,
        request: AddProductToWishlistRequest
    ): ResultWrapper<BaseResponse> {
        return safeApiCall { webServices.addProductToWishlist(token,request).toBaseResponse() }
    }
}