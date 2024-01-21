package com.example.data.repositoryImp.auth.login

import com.example.data.dataSource.auth.login.LoginDataSource
import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import com.example.domain.repository.auth.LoginRepository
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val loginDataSource: LoginDataSource
) :LoginRepository{
    override suspend fun loginWithEmailAndPassword(loginUser: User): AuthResponse {
        return loginDataSource.loginWithEmailAndPassword(loginUser)
    }
}