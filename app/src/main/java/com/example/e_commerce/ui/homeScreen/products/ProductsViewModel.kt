package com.example.e_commerce.ui.homeScreen.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.product.GetProductsUseCase
import com.example.domain.usecases.wishlist.AddProductToWishlistUseCase
import com.example.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.example.e_commerce.ui.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase
) : ViewModel(), ProductsContract.ViewModel {
    private var _event = SingleLiveData<ProductsContract.Event>()
    override val event: SingleLiveData<ProductsContract.Event>
        get() = _event

    private var _state = SingleLiveData<ProductsContract.State>()
    override val state: SingleLiveData<ProductsContract.State>
        get() = _state


    override fun invokeAction(action: ProductsContract.Action) {
        when (action) {
            is ProductsContract.Action.LoadProducts ->{
                loadProducts(action.categoryName , action.token)
            }
            is ProductsContract.Action.DeleteProductFromWishlist -> {
                deleteProductFromWishlist(
                    token =  action.token,
                    productId =   action.productId
                )
            }

            is ProductsContract.Action.AddProductToWishlist -> {
                addProductToWishlist(productId = action.productId, token = action.token)
            }
        }
    }

    private fun addProductToWishlist(token: String, productId: String) {
        viewModelScope.launch {
            when (addProductToWishlistUseCase.invoke(token, productId)) {
                is ResultWrapper.Success -> {
                    _state.postValue(
                        ProductsContract.State.ProductAddedToWishlistSuccessfully
                    )
                }

                else -> {
                    _state.postValue(
                        ProductsContract.State.AddingProductToWishlistFailed
                    )
                }
            }
        }
    }

    private fun loadProducts(categoryName: String, token: String) {
        viewModelScope.launch {
            _state.postValue(ProductsContract.State.LoadingState("Loading.."))
            when (val resultWrapper = productsUseCase.invoke(categoryName, token)) {
                is ResultWrapper.Success -> {
                    _state.postValue(ProductsContract.State.SuccessState(resultWrapper.data))
                }

                is ResultWrapper.Error -> {
                    _state.postValue(
                        ProductsContract.State.FailedState(
                            resultWrapper.exception.localizedMessage ?: ""
                        )
                    )
                }

                else -> {}
            }
        }
    }

    private fun deleteProductFromWishlist(productId:String,token:String) {
        viewModelScope.launch {
            when(deleteProductFromWishlistUseCase.invoke(token, productId)){
                is ResultWrapper.Success->{
                  _state.postValue(
                      ProductsContract.State.ProductRemovedFromWishlistSuccessfully
                  )
                }
                else->{
                    _state.postValue(
                        ProductsContract.State.RemovingProductFromWishlistFailed
                    )
                }
            }
        }
    }
}
