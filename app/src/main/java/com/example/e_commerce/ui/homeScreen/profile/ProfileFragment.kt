package com.example.e_commerce.ui.homeScreen.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentProfileBinding
import com.example.e_commerce.ui.TokenManager
import com.example.e_commerce.ui.login.LoginActivity

class ProfileFragment: Fragment() {
    private lateinit var viewBinding: FragmentProfileBinding
    private lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentProfileBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenManager = TokenManager(requireContext())
        initViews()
    }

    private fun initViews() {
        viewBinding.logout.setOnClickListener{
           logout()
        }

    }

    private fun logout() {
        tokenManager.saveToken("")
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }

}