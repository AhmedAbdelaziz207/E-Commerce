package com.example.domain.usecases.auth

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import com.example.domain.repository.auth.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
){
    suspend fun invoke(loginUser: User):AuthResponse{
        return loginRepository.loginWithEmailAndPassword(loginUser)
    }

}