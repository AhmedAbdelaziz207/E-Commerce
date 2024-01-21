package com.example.domain.repository.product

import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product

interface ProductsRepository {
    suspend fun getProducts():ResultWrapper<List<Product?>?>
}