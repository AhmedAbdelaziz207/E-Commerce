package com.example.domain.repository.auth

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User

interface RegisterRepository {
    suspend fun createAccount(user: User):AuthResponse
}