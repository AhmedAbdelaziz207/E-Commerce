package com.example.e_commerce.ui.homeScreen.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.category.Category
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.FragmentProductsBinding
import com.example.e_commerce.ui.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductsViewModel
    private val productsAdapter: ProductsAdapter by lazy {
        ProductsAdapter(null)
    }
    private lateinit var token: String
    private val args: ProductsFragmentArgs by navArgs()
    private var productsCategory: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsCategory = args.category
        token = TokenManager(requireContext()).getToken()!!
        viewModel.invokeAction(
            ProductsContract.Action.LoadProducts(
                categoryName = productsCategory?.name!!,
                token
            )
        )
        initViews()
    }


    private fun initViews() {

        handelProductsAdapter()
        observeOnLiveData()

    }

    private fun handelProductsAdapter() {
        binding.productsRecycler.adapter = productsAdapter
        productsAdapter.onProductClickListener = ProductsAdapter.OnProductClickListener { product, _ ->
            val action =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(product)
            findNavController().navigate(action)
        }

        productsAdapter.onAddOrRemoveProductToWishlistClickListener =
            ProductsAdapter.OnProductClickListener { product , position  ->
                if (product.inWishlist) {
                    product.inWishlist = false
                    products?.set(position, product)
                    productsAdapter.setData(products)

                    viewModel.invokeAction(
                        ProductsContract.Action.DeleteProductFromWishlist(
                            token = token,
                            productId = product.id ?: ""
                        )
                    )
                } else {
                    viewModel.invokeAction(
                        ProductsContract.Action.AddProductToWishlist(
                            token = token,
                            productId = product.id ?: ""
                        )
                    )
                }


            }
    }

    private fun observeOnLiveData() {
        viewModel.state.observe(viewLifecycleOwner) {
            handleStates(it)
        }

    }

    var products:MutableList<Product?>? = mutableListOf()
    private fun handleStates(state: ProductsContract.State) {
        when (state) {
            is ProductsContract.State.SuccessState -> {
                products = state.products?.toMutableList()

                handleSuccess(products)
            }

            is ProductsContract.State.FailedState -> {
                handleFailed()
            }

            is ProductsContract.State.LoadingState -> {
                handleLoading()
            }

            is ProductsContract.State.AddingProductToWishlistFailed -> {
                showToastMessage("Failed To Add Product")
            }

            is ProductsContract.State.ProductAddedToWishlistSuccessfully -> {
                showToastMessage("Product Added Successfully")
            }

            ProductsContract.State.ProductRemovedFromWishlistSuccessfully -> {
                showToastMessage("Product Removed Successfully")

            }

            ProductsContract.State.RemovingProductFromWishlistFailed -> {
                showToastMessage("Failed To Remove Product")
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    private fun handleFailed() {
        binding.successView.isVisible = false
        binding.loadingView.isVisible = false
        binding.notAvailable.isVisible = false
        binding.failedView.isVisible = true

        binding.tryAgainBtn.setOnClickListener {
            viewModel.invokeAction(
                ProductsContract.Action.LoadProducts(
                    productsCategory?.name!!,
                    token
                )
            )
        }

    }


    private fun handleLoading() {
        binding.successView.isVisible = false
        binding.loadingView.isVisible = true
        binding.notAvailable.isVisible = false
        binding.failedView.isVisible = false
    }

    private fun handleSuccess(products: List<Product?>?) {
        Log.e("Products", products.toString())

        binding.loadingView.isVisible = false
        binding.failedView.isVisible = false
        if (products!!.isEmpty()) {
            binding.successView.isVisible = false
            binding.notAvailable.isVisible = true
        } else {
            binding.successView.isVisible = true
            binding.notAvailable.isVisible = false
            productsAdapter.setData(products)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}