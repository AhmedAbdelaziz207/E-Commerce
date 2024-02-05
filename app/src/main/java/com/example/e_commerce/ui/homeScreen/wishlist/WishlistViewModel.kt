package com.example.e_commerce.ui.homeScreen.wishlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.example.domain.usecases.wishlist.GetLoggedUserWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase
) : WishlistContract.ViewModel, ViewModel() {
    private val _state = MutableLiveData<WishlistContract.State>()
    override val state: MutableLiveData<WishlistContract.State>
        get() = _state

    override fun invokeAction(action: WishlistContract.Action) {
        when (action) {
            is WishlistContract.Action.GetLoggedUserWishlist -> {
                getLoggedUserWishlist(action.token)
            }

            is WishlistContract.Action.DeleteProductFromWishlist -> {
                deleteProductFromWishlist(action.productId,action.token)
            }
        }
    }

    private fun deleteProductFromWishlist(productId:String,token:String) {
        viewModelScope.launch {
            when(val result = deleteProductFromWishlistUseCase.invoke(token, productId)){
                is ResultWrapper.Success->{

                }
                else->{

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
                    Log.e("LoggedUserWishlist", wishlistResponse.toString() )
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