package com.example.domain.usecases.cart

import com.example.domain.common.ResultWrapper
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import com.example.domain.repository.cart.CartRepository
import javax.inject.Inject

class DeleteProductFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
){
    suspend fun invoke(token:String,productId:String):ResultWrapper<LoggedCartResponse>{
        return cartRepository.removeProductFromCart(token, productId)
    }
}