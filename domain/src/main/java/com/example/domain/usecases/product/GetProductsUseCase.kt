package com.example.domain.usecases.product

import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product
import com.example.domain.repository.product.ProductsRepository
import com.example.domain.usecases.wishlist.GetLoggedUserWishlistUseCase
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase
) {
    private suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        return productsRepository.getProducts()
    }

    private suspend fun getLoggedUserWishlist(token: String): List<Product> {
        return when (val result = getLoggedUserWishlistUseCase.invoke(token)) {
            is ResultWrapper.Success -> {
                result.data.products
            }

            else -> {
                listOf()
            }
        }
    }

    suspend fun invoke(categoryName: String, token: String): ResultWrapper<List<Product?>?> {
        return when (val resultWrapper = getProducts()) {
            is ResultWrapper.Success -> {
                val data = resultWrapper.data?.filter { product ->
                    product?.category?.name == categoryName
                }
                val wishlistProducts = getLoggedUserWishlist(token)
                data?.forEach { product ->
                    // Assuming there's some unique identifier like productId to compare products
                    val inWishlist = wishlistProducts.any { wishlistProduct ->
                        product?.id == wishlistProduct.id
                    }
                    product?.inWishlist = inWishlist
                }
                ResultWrapper.Success(data)
            }
            else -> resultWrapper
        }
    }
}