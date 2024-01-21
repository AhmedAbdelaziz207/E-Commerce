package com.example.data.dataSourceImp.product

import com.example.data.dataSource.product.ProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
   abstract fun provideProductsDataSource(
        productsDataSourceImp: ProductsDataSourceImp
    ):ProductsDataSource
}