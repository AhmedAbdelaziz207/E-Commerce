package com.example.e_commerce.ui.homeScreen.products

import com.example.domain.model.product.Product
import com.example.e_commerce.ui.utils.SingleLiveData


class ProductsContract {

    interface ViewModel {
        val state: SingleLiveData<State>
        val event: SingleLiveData<Event>
        fun invokeAction(action: Action)
    }


    sealed class Action {
        class LoadProducts(val categoryName: String, val token: String) : Action()
        class AddProductToWishlist(val token:String, val productId : String) : Action()
        class DeleteProductFromWishlist(val token:String, val productId : String) : Action()

    }

    sealed class State {
        class SuccessState(val products: List<Product?>?) : State()
        class FailedState(val message: String) : State()
        class LoadingState(val message: String) : State()

        data object ProductAddedToWishlistSuccessfully : State()
        data object ProductRemovedFromWishlistSuccessfully : State()
        data object RemovingProductFromWishlistFailed : State()
        data object AddingProductToWishlistFailed : State()


    }

    sealed class Event {
        data object NavigateToProductsDetails : Event()
    }
}

