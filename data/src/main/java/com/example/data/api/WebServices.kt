package com.example.data.api

import com.example.data.model.auth.AuthResponseDto
import com.example.data.model.category.CategoryResponse
import com.example.data.model.product.ProductResponse
import com.example.domain.model.auth.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebServices {
    @GET(APIConstants.CATEGORIES_API)
    suspend fun getCategories():CategoryResponse

    @GET(APIConstants.PRODUCTS_API)
    suspend fun getProducts(): ProductResponse

    @POST(APIConstants.SIGN_UP)
    suspend fun signUp(@Body user: User):AuthResponseDto

    @POST(APIConstants.SIGN_IN)
    suspend fun signIn(@Body user: User):AuthResponseDto
}