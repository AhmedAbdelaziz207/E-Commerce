package com.example.data.repositoryImp.category

import com.example.domain.repository.category.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun provideCategoriesRepository(
        categoriesRepositoryImp: CategoriesRepositoryImp
    ): CategoriesRepository
}