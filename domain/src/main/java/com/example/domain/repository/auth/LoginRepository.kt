package com.example.domain.repository.auth

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User

interface LoginRepository {
    suspend fun loginWithEmailAndPassword(loginUser: User):AuthResponse
}