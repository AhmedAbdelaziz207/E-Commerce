package com.example.data.repositoryImp.product

import androidx.lifecycle.ViewModel
import com.example.domain.repository.product.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideProductsRepository(
        productsRepositoryImp: ProductsRepositoryImp
    ):ProductsRepository
}