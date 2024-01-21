package com.example.e_commerce.ui.homeScreen.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.product.Product

class ProductDetailsViewModel: ViewModel(),ProductDetailsContract.ViewModel{
    var product:Product? = null
    var quantity = MutableLiveData<Int>(1)


    private val _event = MutableLiveData<ProductDetailsContract.Event>()
    override val event: MutableLiveData<ProductDetailsContract.Event>
        get() = _event
    private val _state = MutableLiveData<ProductDetailsContract.State>()
    override val state: MutableLiveData<ProductDetailsContract.State>
        get() = _state

    override fun invokeAction(action: ProductDetailsContract.Action) {
        // add to cart
    }

    fun increaseCount(){
        quantity.value = quantity.value?.plus(1)
    }

    fun decreaseCount(){
        if (quantity.value!! <= 1 ) return
        quantity.value = quantity.value?.minus(1)
    }



}