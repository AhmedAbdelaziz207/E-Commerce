package com.example.e_commerce.ui.homeScreen.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.cart.AddToCartUseCase
import com.example.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.example.domain.usecases.wishlist.GetLoggedUserWishlistUseCase
import com.example.e_commerce.ui.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : WishlistContract.ViewModel, ViewModel() {
    private val TAG = "LoggedUserWishlist"
    private val _state = SingleLiveData<WishlistContract.State>()
    override val state: SingleLiveData<WishlistContract.State>
        get() = _state

    override fun invokeAction(action: WishlistContract.Action) {
        when (action) {
            is WishlistContract.Action.GetLoggedUserWishlist -> {
                getLoggedUserWishlist(action.token)
            }

            is WishlistContract.Action.TransferProductFromWishlistToCart -> {
                transferProductFromWishlistToCart(action.token,action.productId)
            }

            is WishlistContract.Action.DeleteProductFromWishlist -> {
                deleteProductFromWishlist(action.productId,action.token)
            }
        }
    }

    private fun transferProductFromWishlistToCart(token: String,productId: String) {
        viewModelScope.launch {
            when(addToCartUseCase.invoke(token, productId)){
                is ResultWrapper.Success->{
                    _state.postValue(
                        WishlistContract.State.ProductRemovedFromWishlistSuccessfully
                    )
                    invokeAction(WishlistContract.Action.DeleteProductFromWishlist(productId, token))
                }
                else->{
                    _state.postValue(
                        WishlistContract.State.ProductRemovedFromWishlistFailed
                    )
                }
            }
        }
    }

    private fun deleteProductFromWishlist(productId:String,token:String) {
        viewModelScope.launch {
            when(deleteProductFromWishlistUseCase.invoke(token, productId)){
                is ResultWrapper.Success->{
                    invokeAction(WishlistContract.Action.GetLoggedUserWishlist(token))
                    _state.postValue(
                        WishlistContract.State.ProductRemovedFromWishlistSuccessfully
                    )
                }
                else->{
                    _state.postValue(
                        WishlistContract.State.ProductRemovedFromWishlistFailed
                    )
                    Log.e(TAG, "deleteProductFromWishlistFailed " )
                }
            }
        }
    }

    private fun getLoggedUserWishlist(token: String) {
        viewModelScope.launch {
            when (val result = getLoggedUserWishlistUseCase.invoke(token)) {
                is ResultWrapper.Error -> {
                    _state.postValue(
                        WishlistContract.State.FailedState(
                            result.exception.message ?: result.exception.localizedMessage
                        )
                    )
                }

                is ResultWrapper.ServerError -> {
                    _state.postValue(
                        WishlistContract.State.FailedState(
                            result.exception.message ?: result.exception.localizedMessage
                        )
                    )
                }

                ResultWrapper.Loading -> {
                    _state.postValue(
                        WishlistContract.State.LoadingState
                    )
                }

                is ResultWrapper.Success -> {
                    val wishlistResponse = result.data
                    Log.e(TAG, wishlistResponse.toString() )
                    _state.postValue(
                        WishlistContract.State.SuccessState(
                            result.data.products
                        )
                    )
                }
            }
        }

    }


}