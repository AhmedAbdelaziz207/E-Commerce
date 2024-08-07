package com.example.e_commerce.ui.homeScreen.wishlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.FragmentWishlistBinding
import com.example.e_commerce.ui.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {
    private val viewBinding: FragmentWishlistBinding by lazy {
        FragmentWishlistBinding.inflate(layoutInflater)
    }
    private val viewModel: WishlistViewModel by lazy {
        ViewModelProvider(this)[WishlistViewModel::class.java]
    }
    private val wishlistAdapter: WishlistProductsAdapter by lazy {
        WishlistProductsAdapter(null)
    }

    lateinit var token: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = TokenManager(requireContext()).getToken()!!

        viewModel.invokeAction(WishlistContract.Action.GetLoggedUserWishlist(token))
        observeOnLiveData()

        initViews()
    }

    private fun initViews() {
        handelWishlistAdapter()




        Log.e("WishlistToken", token.toString())
    }

    private fun handelWishlistAdapter() {
        viewBinding.wishlistRecyclerview.adapter = wishlistAdapter

        // handel remove from wishlist click
        wishlistAdapter.onRemoveFromWishlistClickListener =
            WishlistProductsAdapter.OnProductClickListener { product ->
                viewModel.invokeAction(
                    WishlistContract.Action.DeleteProductFromWishlist(
                        token = token,
                        productId = product?.id ?: ""
                    )
                )
            }

        // handel transfer product from wishlist to cart \

        wishlistAdapter.onAddToCartClickListener =
            WishlistProductsAdapter.OnProductClickListener { product ->
                viewModel.invokeAction(
                    WishlistContract.Action.TransferProductFromWishlistToCart(
                        token = token,
                        productId = product?.id ?: ""
                    )
                )
            }
    }

    private fun observeOnLiveData() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            renderStates(state)
        }
    }

    private fun renderStates(state: WishlistContract.State) {
        when (state) {
            is WishlistContract.State.FailedState -> {
                Log.e("Wishlist", "Failed")
                handelFailed(state.message)
            }

            WishlistContract.State.LoadingState -> {
                Log.e("Wishlist", "Loading")
                handelLoading()
            }

            is WishlistContract.State.SuccessState -> {
                Log.e("Wishlist", "Success")

                handelSuccess(state.products)
            }

            WishlistContract.State.ProductAddedToCartFailed -> {
                Toast.makeText(context, "ProductAddedToCartFailed", Toast.LENGTH_SHORT).show()
            }

            WishlistContract.State.ProductAddedToCartSuccessfully -> {
                Toast.makeText(context, "ProductAddedToCartSuccessfully", Toast.LENGTH_SHORT).show()

            }

            WishlistContract.State.ProductRemovedFromWishlistFailed -> {
                Toast.makeText(context, "ProductRemovedFromWishlistFailed", Toast.LENGTH_SHORT)
                    .show()

            }

            WishlistContract.State.ProductRemovedFromWishlistSuccessfully -> {
                Toast.makeText(
                    context,
                    "ProductRemovedFromWishlistSuccessfully",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    private fun handelSuccess(products: List<Product?>?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = true
        wishlistAdapter.setData(products)
    }

    private fun handelLoading() {
        viewBinding.loadingView.isVisible = true
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = false
    }

    private fun handelFailed(message: String?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = true
        viewBinding.successView.isVisible = false

        viewBinding.errorMessage.text = message

        viewBinding.tryAgainBtn.setOnClickListener {
            viewModel.invokeAction(WishlistContract.Action.GetLoggedUserWishlist(token))
        }
    }
}

