package com.example.e_commerce.ui.homeScreen.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.cart.DeleteProductFromCartUseCase
import com.example.domain.usecases.cart.GetLoggedCartUseCase
import com.example.e_commerce.ui.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getLoggedCartUseCase: GetLoggedCartUseCase,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase
) : ViewModel(), CartContract.ViewModel {
    var token: String? = null
    private val _event = SingleLiveData<CartContract.Event>()
    override val event: SingleLiveData<CartContract.Event>
        get() = _event
    private val _state = SingleLiveData<CartContract.State>()
    override val state: SingleLiveData<CartContract.State>
        get() = _state

    override fun invokeAction(action: CartContract.Action) {
        when (action) {
            is CartContract.Action.Checkout -> {
            }

            CartContract.Action.LoadCartProducts -> {
                getAllProductsFromUserCart()
            }

            is CartContract.Action.DeleteCartProduct -> {
                deleteProductFromCart(action.productId)
            }
        }
    }

    private fun deleteProductFromCart(productId: String) {
        viewModelScope.launch {
            when (val result = deleteProductFromCartUseCase.invoke(token!!, productId)) {
                is ResultWrapper.Success -> {
                    _state.postValue(
                        CartContract.State.Success(result.data)
                    )
                }

                else -> {

                }
            }
        }
    }

    private fun getAllProductsFromUserCart() {
        viewModelScope.launch {
            when (val result = getLoggedCartUseCase.getLoggedCart(token!!)) {

                is ResultWrapper.Error -> {
                    Log.e("Cart", result.exception.toString())
                    _state.postValue(
                        CartContract.State.Failed(
                            result.exception.message ?: result.exception.localizedMessage
                        )
                    )
                }

                is ResultWrapper.ServerError -> {
                    Log.e("Cart", result.exception.toString())

                    _state.postValue(
                        CartContract.State.Failed(
                            result.exception.message ?: result.exception.localizedMessage
                        )
                    )
                }

                is ResultWrapper.Success -> {
                    Log.e("Cart", result.data.toString())
                    _state.postValue(
                        CartContract.State.Success(result.data)
                    )
                }

                ResultWrapper.Loading -> {
                    _state.postValue(
                        CartContract.State.Loading
                    )
                }
            }
        }
    }
}