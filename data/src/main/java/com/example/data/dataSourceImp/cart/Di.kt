package com.example.data.dataSourceImp.cart

import com.example.data.dataSource.cart.CartDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideCartDatSource(dataSourceImp: CartDataSourceImp): CartDataSource
}