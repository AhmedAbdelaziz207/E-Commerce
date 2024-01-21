package com.example.e_commerce.ui.register


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.data.provider.SessionProvider
import com.example.e_commerce.databinding.ActivityRegisterBinding
import com.example.e_commerce.ui.TokenManager
import com.example.e_commerce.ui.homeScreen.HomeScreen
import com.example.e_commerce.ui.login.LoginActivity
import com.example.e_commerce.ui.showErrorDialog
import com.example.e_commerce.ui.showSuccessDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var tokenManager:TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        tokenManager = TokenManager(this)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        viewBinding.viewModel = this.viewModel
        viewBinding.action = RegisterContract.Action.CreateAccount
        viewBinding.lifecycleOwner = this



        observeOnLiveData()


    }


    private fun observeOnLiveData() {
        viewModel.state.observe(this) { state ->
            renderState(state)
        }
        viewModel.event.observe(this){event->
            handleEvents(event)
        }
    }

    private fun handleEvents(event: RegisterContract.Event) {
        when(event){
            RegisterContract.Event.NavigateToHomeScreen -> navigateToHome()
            RegisterContract.Event.NavigateToLoginScreen -> navigateToLogin()
        }
    }


    private fun renderState(state: RegisterContract.State) {
        when (state) {
            is RegisterContract.State.LoadingState -> {
            }

            is RegisterContract.State.SuccessState -> {
                val user = state.authResponse.user
                SessionProvider.user = user
                tokenManager.saveToken(state.authResponse.token!!)
                showSuccessDialog(
                    title = "Sign up Successful",
                    message = "Hello ${user?.name} "
                ) {
                    navigateToHome()
                }

            }

            is RegisterContract.State.FailedState -> {
                showErrorDialog(
                    title = "Sign up Failed ",
                    message = state.message,
                ) {
                    // dismiss dialog
                }
            }
        }

    }

    private fun navigateToHome() {
        val intent = Intent(this,HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLogin() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}