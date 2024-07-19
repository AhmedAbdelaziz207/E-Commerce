package com.example.domain.usecases.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import com.example.domain.repository.cart.CartRepository
import javax.inject.Inject

class GetLoggedCartUseCase@Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun getLoggedCart(token : String ):ResultWrapper<LoggedCartResponse> {
        return cartRepository.getLoggedCart(token)
    }
}