package com.example.domain.usecases.wishlist

import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse
import com.example.domain.model.wishlist.AddProductToWishlistRequest
import com.example.domain.repository.wishlist.WishlistRepository
import javax.inject.Inject

class AddProductToWishlistUseCase@Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    suspend fun invoke(token:String,productId:String):ResultWrapper<BaseResponse>{
        val request = AddProductToWishlistRequest(productId)
       return wishlistRepository.addProductToWishlist(token,request)
    }
}