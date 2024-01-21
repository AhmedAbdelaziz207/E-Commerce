package com.example.data.dataSource.product

import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product

interface ProductsDataSource {
   suspend fun getProducts():ResultWrapper<List<Product?>?>
}