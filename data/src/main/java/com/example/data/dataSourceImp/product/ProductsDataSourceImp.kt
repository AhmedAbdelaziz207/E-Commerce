package com.example.data.dataSourceImp.product

import com.example.data.api.WebServices
import com.example.data.dataSource.product.ProductsDataSource
import com.example.data.safeApiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product
import javax.inject.Inject

class ProductsDataSourceImp @Inject constructor(
   private val webServices: WebServices)
    : ProductsDataSource {
    override suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        return safeApiCall {
            webServices.getProducts().data?.map {
                it?.toProduct()
            }
        }
    }
}