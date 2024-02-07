package com.example.e_commerce.ui.homeScreen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.category.GetCategoriesUseCase
import com.example.domain.usecases.product.GetProductsUseCase
import com.example.domain.usecases.wishlist.AddProductToWishlistUseCase
import com.example.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.example.e_commerce.ui.homeScreen.products.ProductsContract
import com.example.e_commerce.ui.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase,
    private val productsUseCase: GetProductsUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase
) : ViewModel(), HomeFragmentContract.ViewModel {
    private val _event = SingleLiveData<HomeFragmentContract.Event>()
    override val event: SingleLiveData<HomeFragmentContract.Event>
        get() = _event
    private val _state = SingleLiveData<HomeFragmentContract.State>()
    override val state: SingleLiveData<HomeFragmentContract.State>
        get() = _state

    override fun invokeAction(action: HomeFragmentContract.Action) {
        when (action) {
            is HomeFragmentContract.Action.LoadCategories -> {
                loadCategories()
            }

            is HomeFragmentContract.Action.LoadProducts -> {
                loadProducts(action.token)
            }

            is HomeFragmentContract.Action.AddProductToWishlist -> {
                addProductToWishlist(action.token, action.productId)
            }

            is HomeFragmentContract.Action.RemoveProductFromWishlist -> {
                deleteProductFromWishlist(productId = action.productId, token = action.token)
            }
        }
    }

    private fun addProductToWishlist(token: String, productId: String) {
        viewModelScope.launch {
            when (addProductToWishlistUseCase.invoke(token, productId)) {
                is ResultWrapper.Success -> {
                    _state.postValue(
                        HomeFragmentContract.State.ProductAddedToWishlistSuccessfully
                    )
                }

                else -> {
                    _state.postValue(
                        HomeFragmentContract.State.AddingProductToWishlistFailed
                    )
                }
            }
        }
    }

    private fun loadProducts(token: String) {
        viewModelScope.launch {

            when (val resultWrapper = productsUseCase.invoke("Electronics", token)) {
                is ResultWrapper.Success -> {
                    Log.e("HomeFragment", resultWrapper.data.toString())
                    _state.postValue(HomeFragmentContract.State.ProductsSuccessState(resultWrapper.data))
                }

                is ResultWrapper.Error -> {
                    _state.postValue(
                        HomeFragmentContract.State.FailedState(
                            resultWrapper.exception.localizedMessage ?: ""
                        )
                    )

                }

                else -> {

                }
            }
        }
    }

    private fun loadCategories() {

        viewModelScope.launch {
            _state.postValue(HomeFragmentContract.State.LoadingState("Loading"))

            when (val resultWrapper = categoriesUseCase.invoke()) {
                is ResultWrapper.Success -> {
                    println(resultWrapper.data)
                    _state.postValue(
                        HomeFragmentContract.State.CategoriesSuccessState(
                            resultWrapper.data
                        )
                    )
                }

                is ResultWrapper.Error -> {
                    _state.postValue(HomeFragmentContract.State.FailedState("oops, something went wrong.."))
                }

                else -> {}
            }

        }
    }

    fun navigateToCategories() {
        _event.postValue(
            HomeFragmentContract.Event.NavigateToCategoriesScreen
        )
    }

    private fun deleteProductFromWishlist(productId: String, token: String) {
        viewModelScope.launch {
            when (deleteProductFromWishlistUseCase.invoke(token, productId)) {
                is ResultWrapper.Success -> {
                    _state.postValue(
                        HomeFragmentContract.State.ProductRemovedFromWishlistSuccessfully
                    )
                }

                else -> {
                    _state.postValue(
                        HomeFragmentContract.State.RemovingProductFromWishlistFailed
                    )
                }
            }
        }
    }


}