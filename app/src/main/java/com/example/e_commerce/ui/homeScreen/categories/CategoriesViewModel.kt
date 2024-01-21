package com.example.e_commerce.ui.homeScreen.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.category.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase
):ViewModel(),CategoriesContract.ViewModel{
    private val _state = MutableLiveData<CategoriesContract.State>()
    override val state: MutableLiveData<CategoriesContract.State>
        get() = _state

    private val _event = MutableLiveData<CategoriesContract.Event>()
    override val event: MutableLiveData<CategoriesContract.Event>
        get() = _event

    override fun invokeAction(action: CategoriesContract.Action) {
        when(action){
            is CategoriesContract.Action.LoadCategories-> loadCategories()
        }
    }

    private fun loadCategories(){

            viewModelScope.launch {

                _state.postValue(CategoriesContract.State.LoadingState("Loading.."))


                when(val resultWrapper =  categoriesUseCase.invoke()){
                    is ResultWrapper.Success->{
                        println(resultWrapper.data)
                        _state.postValue(CategoriesContract.State.SuccessState(resultWrapper.data))
                    }
                    is ResultWrapper.Error->{
                        _state.postValue(CategoriesContract.State.FailedState("oops, something went wrong.."))
                    }

                    else -> {}
                }




            }


    }
}