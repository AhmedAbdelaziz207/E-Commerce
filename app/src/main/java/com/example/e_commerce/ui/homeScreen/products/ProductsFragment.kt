package com.example.e_commerce.ui.homeScreen.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.category.Category
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var viewBinding: FragmentProductsBinding
    private lateinit var viewModel: ProductsViewModel
    private val args: ProductsFragmentArgs by navArgs()

    private var productsCategory: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentProductsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsCategory = args.category

        viewModel.invokeAction(ProductsContract.Action.LoadProducts(categoryName = productsCategory?.name!!))
        initViews()
    }


    private lateinit var productsAdapter: ProductsAdapter
    private fun initViews() {
        productsAdapter = ProductsAdapter(null)
        viewBinding.productsRecycler.adapter = productsAdapter

        observeOnLiveData()

        productsAdapter.onProductClickListener = ProductsAdapter.OnProductClickListener { product ->
            val action =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(product)
            findNavController().navigate(action)
        }

    }

    private fun observeOnLiveData() {
        viewModel.state.observe(viewLifecycleOwner) {
            handleStates(it)
        }

    }

    private fun handleStates(state: ProductsContract.State) {
        when (state) {
            is ProductsContract.State.SuccessState -> {
                handleSuccess(state.products)
            }

            is ProductsContract.State.FailedState -> {
                handleFailed()
            }

            is ProductsContract.State.LoadingState -> {
                handleLoading()
            }
        }
    }

    private fun handleFailed() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = false
        viewBinding.notAvailable.isVisible = false
        viewBinding.failedView.isVisible = true

        viewBinding.tryAgainBtn.setOnClickListener {
            viewModel.invokeAction(ProductsContract.Action.LoadProducts(productsCategory?.name!!))
        }

    }

    private fun handleLoading() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = true
        viewBinding.notAvailable.isVisible = false
        viewBinding.failedView.isVisible = false
    }

    private fun handleSuccess(products: List<Product?>?) {
        Log.e("Products", products.toString())

        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = false
        if (products!!.isEmpty()) {
            viewBinding.successView.isVisible = false
            viewBinding.notAvailable.isVisible = true
        } else {
            viewBinding.successView.isVisible = true
            viewBinding.notAvailable.isVisible = false
            productsAdapter.setData(products)
        }


    }
//     lateinit var onProductClickListener: OnProductClickListener
//
//    fun interface OnProductClickListener{
//        fun onProductClick(product: Product)
//    }
//
//
//    companion object {
//        fun getInstance(category: Category?): ProductsFragment {
//            val instance = ProductsFragment()
//            instance.productsCategory = category!!
//            return instance
//        }
//    }


}