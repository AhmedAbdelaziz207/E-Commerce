package com.example.domain.usecases.category

import android.util.Log
import com.example.domain.common.ResultWrapper
import com.example.domain.model.category.Category
import com.example.domain.repository.category.CategoriesRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject
constructor(private val repository: CategoriesRepository) {

    suspend fun invoke():ResultWrapper<List<Category?>?>{
        Log.e("Invoke", repository.getCategories().toString() )
        return repository.getCategories()
    }
}