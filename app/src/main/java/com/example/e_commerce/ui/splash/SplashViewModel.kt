package com.example.e_commerce.ui.splash
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel(), SplashContract.ViewModel {
    private val _event = MutableLiveData<SplashContract.Event>()
    override val event: MutableLiveData<SplashContract.Event>
        get() = _event

    override fun invokeAction(token: String?) {
        println(token)
        redirect(token)
    }

    private fun redirect(token: String?) {
        if (token != null) {
            if (token.isEmpty()) {
                _event.value = SplashContract.Event.NavigateToLoginScreen
            } else {
                _event.value = SplashContract.Event.NavigateToHomeScreen
            }
        }else{
            _event.value = SplashContract.Event.NavigateToLoginScreen
        }
    }
}