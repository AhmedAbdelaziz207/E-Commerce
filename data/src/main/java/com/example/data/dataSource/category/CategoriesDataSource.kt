package com.example.data.dataSource.category

import com.example.domain.common.ResultWrapper
import com.example.domain.model.category.Category

interface CategoriesDataSource {
    suspend fun getCategories():ResultWrapper<List<Category?>?>
}