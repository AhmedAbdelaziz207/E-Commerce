package com.example.e_commerce.ui.homeScreen.home

import com.example.domain.model.category.Category
import com.example.domain.model.product.Product
import com.example.e_commerce.ui.homeScreen.products.ProductsContract
import com.example.e_commerce.ui.utils.SingleLiveData

class HomeFragmentContract {
    interface ViewModel {
        val state: SingleLiveData<State>
        val event: SingleLiveData<Event>
        fun invokeAction(action: Action)
    }

    sealed class Action {
        data object LoadCategories : Action()
        class LoadProducts(val category: Category? = null,val token:String) : Action()

        class AddProductToWishlist(val token:String,val productId:String):Action()
        class RemoveProductFromWishlist(val token:String,val productId:String):Action()
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

        data object ProductAddedToWishlistSuccessfully:State()
        data object AddingProductToWishlistFailed:State()

        data object ProductRemovedFromWishlistSuccessfully : State()
        data object RemovingProductFromWishlistFailed : State()
    }
}


