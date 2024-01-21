package com.example.domain.usecases.product

import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product
import com.example.domain.repository.product.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository) {
    private suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        return productsRepository.getProducts()
    }

    suspend fun getSpecificProducts(categoryName: String): ResultWrapper<List<Product?>?> {
        return when (val resultWrapper = getProducts()) {
            is ResultWrapper.Success -> {
                val data = resultWrapper.data?.filter { product ->
                    product?.category?.name == categoryName
                }
                ResultWrapper.Success(data)
            }

            else -> resultWrapper
        }
    }
}