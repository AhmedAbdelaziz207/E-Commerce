package com.example.data.repositoryImp.auth.register

import com.example.data.dataSource.auth.register.RegisterDataSource
import com.example.domain.model.auth.AuthResponse
import com.example.domain.model.auth.User
import com.example.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(
    private val registerDataSource: RegisterDataSource
):RegisterRepository {
   override suspend fun createAccount(user: User):AuthResponse{
      return  registerDataSource.createAccount(user)
    }
}