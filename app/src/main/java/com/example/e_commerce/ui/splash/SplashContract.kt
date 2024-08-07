package com.example.e_commerce.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.auth.AuthResponse

class SplashContract {
    interface ViewModel{
        val event: MutableLiveData<Event>
        fun invokeAction(token:String?)
    }

    sealed class Event{
        data object NavigateToHomeScreen:Event()
        data object NavigateToLoginScreen:Event()
    }
}