package com.example.data.repositoryImp.cart

import com.example.data.dataSource.cart.CartDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import com.example.domain.repository.cart.CartRepository
import javax.inject.Inject

class CartRepositoryImp@Inject constructor(
    private val dataSourceImp: CartDataSource
):CartRepository {
    override suspend fun getLoggedCart(token: String): ResultWrapper<LoggedCartResponse> {
        return dataSourceImp.getLoggedCart(token)
    }

    override suspend fun addToCart(token: String, request: AddToCartRequest) : ResultWrapper<CartResponse>{
      return dataSourceImp.addToCart(token, request)
    }

    override suspend fun removeProductFromCart(token: String, productId: String): ResultWrapper<LoggedCartResponse> {
        return dataSourceImp.removeProductFromCart(token,productId)
    }
}