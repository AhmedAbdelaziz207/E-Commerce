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
        class AddToCart(val product: Product) : Action()
    }

    sealed class Event {

    }

    sealed class State {
        class SuccessState(val product: Product) : State()
        class FailedState(val message: String) : State()
        class LoadingState(val message: String) : State()
    }
}