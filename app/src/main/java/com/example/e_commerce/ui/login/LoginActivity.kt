package com.example.e_commerce.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.auth.User
import com.example.e_commerce.databinding.ActivityLoginBinding
import com.example.e_commerce.ui.TokenManager
import com.example.e_commerce.ui.homeScreen.HomeScreen
import com.example.e_commerce.ui.register.RegisterActivity
import com.example.e_commerce.ui.showErrorDialog
import com.example.e_commerce.ui.showSuccessDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    private lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.action = LoginContract.Action.Login

        tokenManager = TokenManager(this)
        observeOnLiveData()
    }

    private fun observeOnLiveData() {
        viewModel.state.observe(this) { state ->
            handleStates(state)
        }
        viewModel.event.observe(this) { event ->
            handleEvents(event)
        }
    }

    private fun handleEvents(event: LoginContract.Event) {
        when (event) {
            LoginContract.Event.NavigateToHome -> navigateToHome()
            LoginContract.Event.NavigateToRegister -> navigateToRegister()
        }
    }


    private fun handleStates(state: LoginContract.State) {
        when (state) {
            is LoginContract.State.Failed -> {
                showErrorDialog(
                    title = "login Failed",
                    message = state.message
                ) {}
            }

            LoginContract.State.Loading -> {

            }

            is LoginContract.State.Success -> {
                val response = state.response
                tokenManager.saveToken(response.token!!)
                val user = User(
                    name = response.user?.name,
                    email = response.user?.email,
                    phone = response.user?.phone
                )
                tokenManager.saveUser(user)

                showSuccessDialog(
                    title = "Login Success",
                    message = "Welcome, ${response.user?.name}"
                ) {
                    // dismiss
                    // navigate to home
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}