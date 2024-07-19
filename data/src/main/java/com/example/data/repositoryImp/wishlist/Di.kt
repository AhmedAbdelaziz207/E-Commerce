package com.example.data.repositoryImp.wishlist

import com.example.domain.repository.wishlist.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideWishlistRepository(
        wishlistRepositoryImp: WishlistRepositoryImp
    ):WishlistRepository
}