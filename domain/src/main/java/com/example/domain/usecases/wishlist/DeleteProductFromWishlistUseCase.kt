package com.example.domain.usecases.wishlist

import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse
import com.example.domain.repository.wishlist.WishlistRepository
import javax.inject.Inject

class DeleteProductFromWishlistUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
){
    suspend fun invoke(token:String, productId:String):ResultWrapper<BaseResponse>{
        return wishlistRepository.removeProductFromWishlist(token, productId)
    }
}