package com.example.e_commerce.ui.homeScreen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.category.GetCategoriesUseCase
import com.example.domain.usecases.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val  categoriesUseCase: GetCategoriesUseCase,
    private val productsUseCase: GetProductsUseCase
):ViewModel(),HomeCategoriesContract.ViewModel {
    private val _event = MutableLiveData<HomeCategoriesContract.Event>()
    override val event: MutableLiveData<HomeCategoriesContract.Event>
        get() = _event
    private val _state = MutableLiveData<HomeCategoriesContract.State>()
    override val state: MutableLiveData<HomeCategoriesContract.State>
        get() =_state

    override fun invokeAction(action: HomeCategoriesContract.Action) {
        when(action){
            is HomeCategoriesContract.Action.LoadCategories->{
                loadCategories()
            }
            is HomeCategoriesContract.Action.LoadProducts->{
                loadProducts()
            }

        }
    }

    private fun loadProducts() {
        viewModelScope.launch {

            when(val resultWrapper = productsUseCase.getSpecificProducts("Electronics")){
                is ResultWrapper.Success->{
                    _state.postValue(HomeCategoriesContract.State.ProductsSuccessState(resultWrapper.data))
                }
                is ResultWrapper.Error->{
                    _state.postValue(HomeCategoriesContract.State.FailedState(resultWrapper.exception.localizedMessage?:""))

                }
                else->{

                }
            }
        }
    }

    private fun loadCategories() {

        viewModelScope.launch {
            _state.postValue(HomeCategoriesContract.State.LoadingState("Loading"))

            when(val resultWrapper =  categoriesUseCase.invoke()){
                is ResultWrapper.Success->{
                    println(resultWrapper.data)
                    _state.postValue(HomeCategoriesContract.State.CategoriesSuccessState(resultWrapper.data))
                }
                is ResultWrapper.Error->{
                    _state.postValue(HomeCategoriesContract.State.FailedState("oops, something went wrong.."))
                }

                else -> {}
            }

        }
    }

    fun navigateToCategories(){
        _event.postValue(
            HomeCategoriesContract.Event.NavigateToCategoriesScreen
        )
    }

}