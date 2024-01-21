package com.example.e_commerce.ui.homeScreen.home

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.category.Category
import com.example.domain.model.product.Product

class HomeCategoriesContract {
    interface ViewModel{
        val state : MutableLiveData<State>
        val event : MutableLiveData<Event>
        fun invokeAction(action: Action)
    }

    sealed class Action {
        object LoadCategories : Action()
        class LoadProducts(val category: Category?=null):Action()
    }
    sealed class Event {
        object NavigateToAllCategories : Event()
        object NavigateToProduct : Event()
    }

    sealed class State {
        class CategoriesSuccessState(val categories:List<Category?>?):State()
        class ProductsSuccessState(val products:List<Product?>?):State()
        class FailedState(val message:String):State()
        class LoadingState(val message: String):State()
    }
}