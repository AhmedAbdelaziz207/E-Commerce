package com.example.data.repositoryImp.wishlist

import com.example.data.dataSource.wishlist.WishlistDataSource
import com.example.data.model.wishlist.WishlistResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.model.baseResponse.BaseResponse
import com.example.domain.model.product.Product
import com.example.domain.repository.wishlist.WishlistRepository
import javax.inject.Inject

class WishlistRepositoryImp@Inject constructor(
    private val wishlistDataSource: WishlistDataSource
) : WishlistRepository {
    override suspend fun getWishlistUserWishlist(token: String): ResultWrapper<WishlistResponse> {
        return wishlistDataSource.getWishlistUserWishlist(token)
    }

    override suspend fun removeProductFromWishlist(
        token: String,
        productId: String
    ): ResultWrapper<BaseResponse> {
       return wishlistDataSource.removeProductFromWishlist(token, productId)
    }
}