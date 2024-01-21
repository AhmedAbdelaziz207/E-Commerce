package com.example.data.dataSource.auth.register

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User

interface RegisterDataSource {
    suspend fun createAccount(user: User): AuthResponse
}