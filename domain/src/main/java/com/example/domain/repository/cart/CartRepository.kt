package com.example.domain.repository.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse

interface CartRepository {
    suspend fun getLoggedCart(token: String):ResultWrapper<LoggedCartResponse>

    suspend fun addToCart(token:String , request: AddToCartRequest): ResultWrapper<CartResponse>
}