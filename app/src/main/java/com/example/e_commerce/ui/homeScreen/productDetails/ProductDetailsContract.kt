package com.example.e_commerce.ui.homeScreen.productDetails

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.product.Product
import com.example.e_commerce.ui.utils.SingleLiveData

sealed class ProductDetailsContract {


    interface ViewModel {
        val state: SingleLiveData<State>
        val event: SingleLiveData<Event>

        fun invokeAction(action: Action)
    }

    sealed class Action {
        class AddProductToCart(val product: Product) : Action()
    }

    sealed class Event {
        data object NavigateToCartScreen:Event()
    }

    sealed class State {

        class FailedToAddProductToCart(val message: String):State()
    }
}