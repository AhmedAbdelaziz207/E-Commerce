package com.example.data.dataSourceImp.category
import android.util.Log
import com.example.data.api.WebServices
import com.example.data.dataSource.category.CategoriesDataSource
import com.example.data.safeApiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.model.category.Category
import javax.inject.Inject

class CategoriesDataSourceImp @Inject constructor(
    private val webServices: WebServices
): CategoriesDataSource {
    override suspend fun getCategories(): ResultWrapper<List<Category?>?> {
        Log.e("DataSource", webServices.getCategories().data.toString() )
     return safeApiCall {
         webServices.getCategories().data?.map {
             it?.toCategory()
         }

     }

    }
}