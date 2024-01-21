package com.example.e_commerce.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.baseResponse.BaseResponse
import com.example.domain.model.auth.User
import com.example.domain.usecases.auth.RegisterUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: RegisterUseCase
) : ViewModel(), RegisterContract.ViewModel {
    val userName = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val rePassword = MutableLiveData<String>()


    val userNameError : MutableLiveData<String?> = MutableLiveData<String?>()
    val phoneError   : MutableLiveData<String?> = MutableLiveData<String?>()
    val emailError  : MutableLiveData<String?> = MutableLiveData<String?>()
    val passwordError : MutableLiveData<String?> = MutableLiveData<String?>()
    val rePasswordError  : MutableLiveData<String?> = MutableLiveData<String?>()

    private var _event = MutableLiveData<RegisterContract.Event>()
    override val event: MutableLiveData<RegisterContract.Event>
        get() = _event

    private var _state = MutableLiveData<RegisterContract.State>()
    override val state: MutableLiveData<RegisterContract.State>
        get() = _state

    override fun invokeAction(action: RegisterContract.Action) {
        when(action){
            RegisterContract.Action.CreateAccount ->  signUp()
        }
    }


    private fun isValidForm(): Boolean {
        var isValid = true
        if (userName.value.isNullOrBlank()) {
            userNameError.value = "Enter Valid Name"
            isValid = false

        }else{
            userNameError.value = null
        }
        if (email.value.isNullOrBlank()) {
            emailError.value = "Enter Valid Mail"
            isValid = false
        }else{
            emailError.value = null
        }
        if (phone.value.isNullOrBlank()) {
            phoneError.value = "Enter Valid Phone Number "
            isValid = false
        }else{
            phoneError.value = null
        }
        if (password.value.isNullOrBlank()) {
            passwordError.value = "Enter Valid Password"
            rePasswordError.value = "Enter Valid Password"
            isValid = false
        }else{
            passwordError.value = null
        }
        if (password.value != rePassword.value) {
            rePasswordError.value = "Password Doesn't Match"
            isValid = false
        }else{
            rePasswordError.value = null
        }
        return isValid
    }

    private fun signUp() {

        if (!isValidForm())
            return
        else {
            val user = User(
                name = userName.value,
                email = email.value,
                phone = phone.value,
                password = password.value,
                rePassword = rePassword.value
            )
            viewModelScope.launch {

                try {
                    val response = signUpUseCase.invoke(user)
                    _state.value = response
                        .let { RegisterContract.State.SuccessState(it) }

                    _event.value = RegisterContract.Event.NavigateToHomeScreen
                } catch (ex: HttpException) {

                    val errorBody = ex.response()?.errorBody()?.string()
                        val response = Gson().fromJson(errorBody, BaseResponse::class.java)
                        _state.value =
                            RegisterContract.State.FailedState(response.errors?.msg ?:response.message?: "something went wrong")

                    ex.printStackTrace()
                } catch (ex: Exception) {
                    _state.value =
                        RegisterContract.State.FailedState(ex.message ?: "something went wrong")
                }
            }
        }
    }

    fun navigateToLogin(){
        _event.value = RegisterContract.Event.NavigateToLoginScreen
    }
}