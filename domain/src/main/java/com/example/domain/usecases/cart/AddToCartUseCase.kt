package com.example.domain.usecases.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.repository.cart.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun invoke(token : String ,  productId: String) : ResultWrapper<CartResponse> {
        val request = AddToCartRequest(productId)
        return cartRepository.addToCart(token, request)
    }
}