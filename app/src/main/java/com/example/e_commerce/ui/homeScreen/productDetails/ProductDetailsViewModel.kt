package com.example.e_commerce.ui.homeScreen.productDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.model.product.Product
import com.example.domain.usecases.cart.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel(), ProductDetailsContract.ViewModel {
    var token : String?= null
    var product: Product? = null
    var quantity = MutableLiveData(1)


    private val _event = MutableLiveData<ProductDetailsContract.Event>()
    override val event: MutableLiveData<ProductDetailsContract.Event>
        get() = _event
    private val _state = MutableLiveData<ProductDetailsContract.State>()
    override val state: MutableLiveData<ProductDetailsContract.State>
        get() = _state

    override fun invokeAction(action: ProductDetailsContract.Action) {
        addProductToCart()

    }



    private fun addProductToCart() {
        viewModelScope.launch {
            Log.e("ViewModel Token ", token.toString() )
            when (val result = addToCartUseCase.invoke(token!!, product?.id!!)) {
                is ResultWrapper.Error -> {
                    _state.postValue(
                        ProductDetailsContract.State.FailedToAddProductToCart(
                            result.exception.message.toString()
                        )
                    )
                }

                is ResultWrapper.ServerError -> {
                    _state.postValue(
                        ProductDetailsContract.State.FailedToAddProductToCart(
                            result.exception.message.toString()
                        )
                    )
                }

                is ResultWrapper.Success -> {
                    _event.postValue(
                        ProductDetailsContract.Event.NavigateToCartScreen
                    )
                }

                is ResultWrapper.Loading -> {

                }
            }
        }
    }

    fun increaseCount() {
        quantity.value = quantity.value?.plus(1)
    }

    fun decreaseCount() {
        if (quantity.value!! <= 1) return
        quantity.value = quantity.value?.minus(1)
    }


}