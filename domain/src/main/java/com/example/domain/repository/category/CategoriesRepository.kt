package com.example.domain.repository.category
import com.example.domain.common.ResultWrapper
import com.example.domain.model.category.Category


interface CategoriesRepository {
   suspend fun getCategories():ResultWrapper<List<Category?>?>
}