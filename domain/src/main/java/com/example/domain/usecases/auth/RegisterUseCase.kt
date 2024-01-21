package com.example.domain.usecases.auth

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import com.example.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
){
   suspend fun invoke(user: User):AuthResponse{
       return registerRepository.createAccount(user)
    }
}