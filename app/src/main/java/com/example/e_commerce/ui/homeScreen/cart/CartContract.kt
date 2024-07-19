package com.example.e_commerce.ui.homeScreen.cart

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.cart.addToCart.CartProduct
import com.example.domain.model.cart.addToCart.CartResponse
import com.example.domain.model.cart.loggedCart.LoggedCartResponse
import com.example.domain.model.product.Product
import com.example.e_commerce.ui.utils.SingleLiveData

sealed class CartContract {

    interface ViewModel {
        val state: SingleLiveData<State>
        val event: SingleLiveData<Event>

        fun invokeAction(action: Action)
    }

    sealed class Action {
        class Checkout(val products:List<Product>):Action()
        data object LoadCartProducts:Action()
        class DeleteCartProduct(val productId: String):Action()
    }

    sealed class Event {
       data object NavigateProductDetails:Event()
    }

    sealed class State {
        data object Loading : State()
        class Success(val response : LoggedCartResponse) : State()
        class Failed(val message: String) : State()
    }
}