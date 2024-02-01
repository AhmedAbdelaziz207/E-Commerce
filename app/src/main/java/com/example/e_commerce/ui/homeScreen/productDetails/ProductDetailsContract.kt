package com.example.e_commerce.ui.homeScreen.productDetails

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.product.Product

sealed class ProductDetailsContract {


    interface ViewModel {
        val state: MutableLiveData<State>
        val event: MutableLiveData<Event>

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