package com.example.data.dataSourceImp.auth.login

import com.example.data.dataSource.auth.login.LoginDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun provideLoginDataSource(
        loginDataSourceImp: LoginDataSourceImp
    ): LoginDataSource
}