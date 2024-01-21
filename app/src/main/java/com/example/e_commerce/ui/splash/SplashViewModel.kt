package com.example.e_commerce.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() ,SplashContract.ViewModel{
    private val _event = MutableLiveData<SplashContract.Event>()
    override val event: MutableLiveData<SplashContract.Event>
        get() = _event

    override fun invokeAction(token: String) {
        redirect(token)
    }
    private fun redirect(token:String){

        if (token.isEmpty()){
            _event.value = SplashContract.Event.NavigateToLoginScreen
        }else{
            _event.value = SplashContract.Event.NavigateToHomeScreen
        }
    }
}