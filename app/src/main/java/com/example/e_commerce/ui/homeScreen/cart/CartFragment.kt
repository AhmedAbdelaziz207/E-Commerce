package com.example.e_commerce.ui.homeScreen.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.cart.addToCart.CartProduct
import com.example.domain.model.cart.loggedCart.ProductsItem
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.FragmentCartBinding
import com.example.e_commerce.ui.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var viewBinding: FragmentCartBinding
    lateinit var viewModel: CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        viewBinding = FragmentCartBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize viewModel
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.token = TokenManager(requireContext()).getToken()
        viewModel.invokeAction(CartContract.Action.LoadCartProducts)



        initViews()
    }
    private val cartAdapter by lazy {
        CartProductAdapter()
    }

    private fun initViews() {
        viewBinding.cartRecyclerView.adapter = cartAdapter
        cartAdapter.onCartProductDeleteClickListener = CartProductAdapter.OnCartProductClickListener { productId ->
            deleteProductFromCart(productId)
        }


        observeOnLiveData()


    }

    private fun deleteProductFromCart(productId: String) {
        viewModel.invokeAction(CartContract.Action.DeleteCartProduct(productId))
    }

    private fun observeOnLiveData() {
        viewModel.state.observe(viewLifecycleOwner){
            renderStates(it)
        }
    }

    private fun renderStates(state: CartContract.State) {
        when(state){
            is CartContract.State.Failed -> {
                handelFailed(state.message)
            }
            CartContract.State.Loading -> {
                handelLoading()

            }
            is CartContract.State.Success -> {
                Log.e("Cart", state.response.toString() )
                val cartProducts = state.response.data?.products
                handelSuccess(cartProducts)
                viewBinding.cartProducts = state.response.data

            }
        }
    }

    private fun handelSuccess(products : List<ProductsItem?>?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = true

        cartAdapter.setData(products)
    }

    private fun handelLoading() {
        viewBinding.loadingView.isVisible = true
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = false
    }

    private fun handelFailed(message:String?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = true
        viewBinding.successView.isVisible = false

        viewBinding.tryAgainBtn.setOnClickListener{
            viewModel.invokeAction(CartContract.Action.LoadCartProducts)
        }
        viewBinding.errorMessage.text = message
    }
}