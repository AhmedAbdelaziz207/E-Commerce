package com.example.e_commerce.ui.homeScreen.wishlist

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.product.Product
import com.example.e_commerce.ui.utils.SingleLiveData

sealed class WishlistContract {
    interface ViewModel{
        val state :SingleLiveData<State>
        fun invokeAction(action:Action)
    }

    sealed class Action{
        class GetLoggedUserWishlist(val token:String):Action()
        class DeleteProductFromWishlist(val productId: String,val token: String):Action()
        class TransferProductFromWishlistToCart(val productId: String, val token: String):Action()
    }
    sealed class State{
        data object LoadingState:State()
        class FailedState(val message:String):State()
        class SuccessState(val products:List<Product?>?):State()

        data object ProductAddedToCartSuccessfully:State()
        data object ProductAddedToCartFailed:State()
        data object ProductRemovedFromWishlistSuccessfully:State()
        data object ProductRemovedFromWishlistFailed:State()
    }
}