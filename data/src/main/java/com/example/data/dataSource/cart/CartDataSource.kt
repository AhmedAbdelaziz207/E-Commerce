package com.example.data.dataSource.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse


interface CartDataSource {
    suspend fun getLoggedCart(token: String):ResultWrapper<LoggedCartResponse>

    suspend fun addToCart(token:String , request: AddToCartRequest):ResultWrapper<CartResponse>

    suspend fun removeProductFromCart(token: String,productId:String):ResultWrapper<LoggedCartResponse>
}