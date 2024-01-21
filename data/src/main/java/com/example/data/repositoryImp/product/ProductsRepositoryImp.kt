package com.example.data.repositoryImp.product

import com.example.data.dataSource.product.ProductsDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product
import com.example.domain.repository.product.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(
    private val productsDataSource: ProductsDataSource
): ProductsRepository {
    override suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        return productsDataSource.getProducts()
    }
}