package com.example.data.dataSourceImp.auth.register

import androidx.lifecycle.ViewModel
import com.example.data.dataSource.auth.register.RegisterDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
  abstract  fun provideRegisterDataSource(
        registerDataSourceImp: RegisterDataSourceImp): RegisterDataSource
}