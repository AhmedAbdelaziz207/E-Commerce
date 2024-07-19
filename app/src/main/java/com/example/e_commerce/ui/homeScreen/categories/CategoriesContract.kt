package com.example.e_commerce.ui.homeScreen.categories

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.category.Category

class CategoriesContract {

    interface ViewModel{
        val state : MutableLiveData<State>
        val event : MutableLiveData<Event>
        fun invokeAction(action: Action)
    }

    sealed class Action {
        object LoadCategories : Action()
    }
    sealed class Event {
        class NavigateToProductScreen (val category: Category): Event()
    }

    sealed class State {
        class SuccessState(val categories:List<Category?>?):State()
        class FailedState(val message:String):State()
        class LoadingState(val message: String):State()
    }
}