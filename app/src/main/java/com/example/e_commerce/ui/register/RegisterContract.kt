package com.example.e_commerce.ui.register

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User

sealed class RegisterContract {
    interface ViewModel {
        val state: MutableLiveData<State>
        val event: MutableLiveData<Event>
        fun invokeAction(action: Action)
    }

    sealed class State {
        class FailedState(val message: String) : State()
        object LoadingState : State()
        class SuccessState(val authResponse: AuthResponse) : State()
    }

    sealed class Action {
        object CreateAccount : Action()
    }

    sealed class Event {
        object NavigateToHomeScreen : Event()

        object NavigateToLoginScreen : Event()

    }
}