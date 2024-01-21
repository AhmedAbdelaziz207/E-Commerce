package com.example.data.dataSource.auth.login

import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User

interface LoginDataSource {
    suspend fun loginWithEmailAndPassword(loginUser: User):AuthResponse
}