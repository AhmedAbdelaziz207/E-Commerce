package com.example.e_commerce.ui.homeScreen.home

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.category.Category
import com.example.domain.model.product.Product
import com.example.e_commerce.ui.utils.SingleLiveData

class HomeCategoriesContract {
    interface ViewModel {
        val state: SingleLiveData<State>
        val event: SingleLiveData<Event>
        fun invokeAction(action: Action)
    }

    sealed class Action {
        data object LoadCategories : Action()
        class LoadProducts(val category: Category? = null) : Action()
    }

    sealed class Event {
        data object NavigateToCategoriesScreen : Event()
        class NavigateToProductsScreen(val category: Category) : Event()
        class NavigateToProductDetails(val product: Product) : Event()
    }

    sealed class State {
        class CategoriesSuccessState(val categories: List<Category?>?) : State()
        class ProductsSuccessState(val products: List<Product?>?) : State()
        class FailedState(val message: String) : State()
        class LoadingState(val message: String) : State()
    }
}