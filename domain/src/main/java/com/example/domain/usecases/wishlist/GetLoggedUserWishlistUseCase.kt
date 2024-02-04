package com.example.domain.usecases.wishlist

import com.example.data.model.wishlist.WishlistResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.repository.wishlist.WishlistRepository
import javax.inject.Inject

class GetLoggedUserWishlistUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    suspend fun invoke(token:String):ResultWrapper<WishlistResponse>{
        return wishlistRepository.getWishlistUserWishlist(token)
    }
}