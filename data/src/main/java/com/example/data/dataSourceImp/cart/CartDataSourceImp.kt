package com.example.data.dataSourceImp.cart

import com.example.data.api.WebServices
import com.example.data.dataSource.cart.CartDataSource
import com.example.data.safeApiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import javax.inject.Inject

class CartDataSourceImp @Inject constructor(
    private val webServices: WebServices
) : CartDataSource {
    override suspend fun getLoggedCart(token: String): ResultWrapper<LoggedCartResponse> {
       return safeApiCall {  webServices.getLoggedUserCart(token)}
    }

    override suspend fun addToCart(token: String, request: AddToCartRequest): ResultWrapper<CartResponse> {
        return safeApiCall { webServices.addToCart(token,  request).toCartResponse() }
    }
}