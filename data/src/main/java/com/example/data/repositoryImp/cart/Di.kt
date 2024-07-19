package com.example.data.repositoryImp.cart

import com.example.domain.repository.cart.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideCartRepository(
        cartRepositoryImp: CartRepositoryImp
    ):CartRepository
}