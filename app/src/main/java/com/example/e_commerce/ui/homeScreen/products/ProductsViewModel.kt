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
) : ViewModel() ,ProductsContract.ViewModel{
    private var _event = MutableLiveData<ProductsContract.Event>()
    override val event: MutableLiveData<ProductsContract.Event>
        get() = _event

    private var _state = MutableLiveData<ProductsContract.State>()
    override val state: MutableLiveData<ProductsContract.State>
        get() = _state



    override fun invokeAction(action: ProductsContract.Action) {
            when(action){
                is ProductsContract.Action.LoadProducts -> loadProducts(action.categoryName)
            }
        }

    private fun loadProducts(categoryName: String) {
        viewModelScope.launch {
            _state.postValue(ProductsContract.State.LoadingState("Loading.."))
            when(val resultWrapper = productsUseCase.getSpecificProducts(categoryName)){
                is ResultWrapper.Success->{
                    _state.postValue(ProductsContract.State.SuccessState(resultWrapper.data))
                }
                is ResultWrapper.Error->{
                    _state.postValue(ProductsContract.State.FailedState(resultWrapper.exception.localizedMessage?:""))
                }

                else -> {}
            }
        }
    }


}
