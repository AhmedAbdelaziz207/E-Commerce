package com.example.data.dataSourceImp.wishlist

import com.example.data.dataSource.wishlist.WishlistDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideWishlistDataSource(
        wishlistDataSourceImp: WishlistDataSourceImp
    ):WishlistDataSource
}