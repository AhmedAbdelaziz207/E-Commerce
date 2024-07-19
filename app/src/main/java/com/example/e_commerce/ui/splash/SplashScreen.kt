package com.example.e_commerce.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ActivityLoginBinding
import com.example.e_commerce.databinding.ActivitySplashBinding
import com.example.e_commerce.ui.TokenManager
import com.example.e_commerce.ui.homeScreen.HomeScreen
import com.example.e_commerce.ui.login.LoginActivity
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var viewModel: SplashViewModel
    lateinit var viewBinding: ActivitySplashBinding
    private lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        tokenManager = TokenManager(this)
        println("token : "+tokenManager.getToken())
        Handler(Looper.getMainLooper()).postDelayed({
            val tokenManager = TokenManager(this)
            tokenManager.saveToken(null)
            viewModel.invokeAction(tokenManager.getToken())
        }, 2000)
        observeOnLiveData()
    }

    private fun observeOnLiveData() {
        viewModel.event.observe(this) { event ->
            handleEvents(event)
        }
    }

    private fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.NavigateToHomeScreen -> navigateToHomeScreen()
            SplashContract.Event.NavigateToLoginScreen -> navigateToLoginScreen()
        }
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}