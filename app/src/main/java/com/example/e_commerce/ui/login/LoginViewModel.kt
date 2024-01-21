package com.example.e_commerce.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.baseResponse.BaseResponse
import com.example.domain.model.auth.User
import com.example.domain.usecases.auth.LoginUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LoginContract.ViewModel {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val emailError: MutableLiveData<String?> = MutableLiveData<String?>()
    val passwordError: MutableLiveData<String?> = MutableLiveData<String?>()

    private val _event = MutableLiveData<LoginContract.Event>()
    override val event: MutableLiveData<LoginContract.Event>
        get() = _event

    private val _state = MutableLiveData<LoginContract.State>()
    override val state: MutableLiveData<LoginContract.State>
        get() = _state

    override fun invokeAction(action: LoginContract.Action) {
        when (action) {
            LoginContract.Action.Login -> login()
        }
    }

    private fun isValidForm(): Boolean {
        var isValid = true
        if (email.value.isNullOrBlank()) {
            emailError.value = "Enter Valid Mail"
            isValid = false
        } else {
            emailError.value = null
        }
        if (password.value.isNullOrBlank()) {
            passwordError.value = "Enter Valid Password"
            isValid = false
        } else {
            passwordError.value = null
        }
        return isValid
    }

    private fun login() {
        if (!isValidForm()) return
        else {
            val user = User(email = email.value, password = password.value)
            viewModelScope.launch {
                try {
                    val response = loginUseCase.invoke(user)
                    _state.value = LoginContract.State.Success(response)
                    _event.value = LoginContract.Event.NavigateToHome
                } catch (ex: HttpException) {
                    val errorBody = ex.response()?.errorBody()?.string()
                    val response = Gson().fromJson(errorBody, BaseResponse::class.java)
                    _state.value = LoginContract.State.Failed(
                        response.errors?.msg ?: response.message ?: "something went wrong"
                    )
                }catch (ex:Exception){
                    _state.value = LoginContract.State.Failed(
                         ex.message ?: "something went wrong"
                    )
                }

            }

        }
    }

    fun navigateToRegister(){
        _event.value = LoginContract.Event.NavigateToRegister
    }
}