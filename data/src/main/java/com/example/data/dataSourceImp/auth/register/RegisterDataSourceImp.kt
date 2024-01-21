package com.example.data.dataSourceImp.auth.register

import com.example.data.api.WebServices
import com.example.data.dataSource.auth.register.RegisterDataSource
import com.example.data.model.auth.AuthResponseDto
import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import javax.inject.Inject

class RegisterDataSourceImp @Inject constructor(
    private val webServices: WebServices
):RegisterDataSource {
   override suspend fun createAccount(user: User): AuthResponse {
        return webServices.signUp(user).toAuthResponse()
    }
}