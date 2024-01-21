package com.example.data.dataSourceImp.auth.login

import com.example.data.api.WebServices
import com.example.data.dataSource.auth.login.LoginDataSource
import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import javax.inject.Inject

class LoginDataSourceImp @Inject constructor(
    private val webServices: WebServices
):LoginDataSource {
    override suspend fun loginWithEmailAndPassword(loginUser: User): AuthResponse {
        return webServices.signIn(loginUser).toAuthResponse()
    }

}