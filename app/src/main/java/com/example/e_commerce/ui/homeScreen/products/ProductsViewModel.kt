package com.example.e_commerce.ui.homeScreen.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase
) : ViewModel() ,ProductDetailsContract.ViewModel{
    private var _event = MutableLiveData<ProductDetailsContract.Event>()
    override val event: MutableLiveData<ProductDetailsContract.Event>
        get() = _event

    private var _state = MutableLiveData<ProductDetailsContract.State>()
    override val state: MutableLiveData<ProductDetailsContract.State>
        get() = _state



    override fun invokeAction(action: ProductDetailsContract.Action) {
            when(action){
                is ProductDetailsContract.Action.LoadProducts -> loadProducts(action.categoryName)
            }
        }

    private fun loadProducts(categoryName: String) {
        viewModelScope.launch {
            _state.postValue(ProductDetailsContract.State.LoadingState("Loading.."))
            when(val resultWrapper = productsUseCase.getSpecificProducts(categoryName)){
                is ResultWrapper.Success->{
                    _state.postValue(ProductDetailsContract.State.SuccessState(resultWrapper.data))
                }
                is ResultWrapper.Error->{
                    _state.postValue(ProductDetailsContract.State.FailedState(resultWrapper.exception.localizedMessage?:""))
                }

                else -> {}
            }
        }
    }


}
