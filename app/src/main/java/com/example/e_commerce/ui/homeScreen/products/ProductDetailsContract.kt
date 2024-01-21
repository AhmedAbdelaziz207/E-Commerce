package com.example.e_commerce.ui.homeScreen.products

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.product.Product


class ProductDetailsContract {

    interface ViewModel{
        val state:MutableLiveData<State>
        val event:MutableLiveData<Event>
        fun invokeAction( action: Action)
    }


    sealed class Action{
        class LoadProducts(val categoryName:String): Action()
    }
    sealed class State{
        class SuccessState(val products:List<Product?>?):State()
        class FailedState(val message:String): State()
        class LoadingState(val message: String):State()
    }
    sealed class Event{
        object NavigateToProductsDetails:Event()
    }
}

