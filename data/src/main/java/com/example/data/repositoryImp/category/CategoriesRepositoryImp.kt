package com.example.data.repositoryImp.category

import com.example.data.dataSource.category.CategoriesDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.category.Category
import com.example.domain.repository.category.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImp @Inject constructor(
    private val categoriesDataSource: CategoriesDataSource
)
    : CategoriesRepository {
    override suspend fun getCategories(): ResultWrapper<List<Category?>?> {
       return categoriesDataSource.getCategories()
    }
}