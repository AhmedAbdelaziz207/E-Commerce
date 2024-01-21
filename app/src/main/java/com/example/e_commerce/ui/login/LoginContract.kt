package com.example.e_commerce.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.auth.AuthResponse

sealed class LoginContract {
    interface ViewModel{
        val state:MutableLiveData<State>
        val event:MutableLiveData<Event>

        fun invokeAction(action: Action)
    }

    sealed class Action{
        object Login:Action()
    }
    sealed class Event{
        object NavigateToHome:Event()
        object NavigateToRegister:Event()
    }
    sealed class State {
        object Loading:State()
        class Failed(val message: String):State()
        class Success(val response: AuthResponse):State()
    }
}