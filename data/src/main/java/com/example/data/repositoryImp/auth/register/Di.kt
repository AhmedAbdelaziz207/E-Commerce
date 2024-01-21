package com.example.data.repositoryImp.auth.register

import com.example.data.dataSourceImp.auth.register.RegisterDataSourceImp
import com.example.domain.repository.auth.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract  fun provideRegisterDataSource(
        registerRepositoryImp: RegisterRepositoryImp): RegisterRepository
}