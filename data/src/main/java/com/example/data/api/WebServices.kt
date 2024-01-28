package com.example.data.api

import com.example.data.model.auth.AuthResponseDto
import com.example.data.model.cart.CartResponseDto
import com.example.data.model.category.CategoryResponse
import com.example.data.model.product.ProductResponse
import com.example.domain.model.auth.User
import com.example.domain.model.cart.addToCart.AddToCartRequest
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header

interface WebServices {
    @GET(APIConstants.CATEGORIES_API)
    suspend fun getCategories(): CategoryResponse

    @GET(APIConstants.PRODUCTS_API)
    suspend fun getProducts(): ProductResponse

    @POST(APIConstants.SIGN_UP_API)
    suspend fun signUp(@Body user: User): AuthResponseDto

    @POST(APIConstants.SIGN_IN_API)
    suspend fun signIn(@Body user: User): AuthResponseDto

    @POST(APIConstants.CART_API)
    suspend fun addToCart(
        @Header("token") token: String,
        @Body request: AddToCartRequest
    ): CartResponseDto

    @GET(APIConstants.CART_API)
    suspend fun getLoggedUserCart(
        @Header("token") token: String,
    ): LoggedCartResponse


}