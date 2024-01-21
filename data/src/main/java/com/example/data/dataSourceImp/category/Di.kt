package com.example.data.dataSourceImp.category

import com.example.data.dataSource.category.CategoriesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideCategoriesDataSource(
        categoriesDataSourceImp: CategoriesDataSourceImp
    ): CategoriesDataSource
}