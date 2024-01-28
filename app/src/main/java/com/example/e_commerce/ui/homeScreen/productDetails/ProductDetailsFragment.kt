package com.example.e_commerce.ui.homeScreen.productDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.FragmentProductDetailsBinding
import com.example.e_commerce.ui.TokenManager
import com.example.e_commerce.ui.homeScreen.productDetails.ProductDetailsContract.State
import com.example.e_commerce.ui.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentProductDetailsBinding
    lateinit var viewModel: ProductDetailsViewModel
    lateinit var product: Product
    private val args: ProductDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        this.product = args.product

        viewModel.product = this.product
       viewModel.token = TokenManager(requireContext()).getToken()

        viewBinding.product = product
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = this.viewModel
        viewBinding.action = ProductDetailsContract.Action.AddProductToCart(product)


        observeOnLiveData()
        setImagesListToImageSlider()
    }

    private fun observeOnLiveData() {
        viewModel.event.observe(viewLifecycleOwner) {
            renderEvents(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            renderStates(it)
        }
    }

    private fun renderEvents(event: ProductDetailsContract.Event) {
        when (event) {
            ProductDetailsContract.Event.NavigateToCartScreen -> navigateToCartScreen()
        }
    }

    private fun renderStates(state: State) {
        when (state) {
            is State.FailedToAddProductToCart -> {
                showDialog("Failed To add Product", state.message) {}
            }
        }
    }

    private fun navigateToCartScreen() {
        val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartFragment()
        findNavController().navigate(action)
    }

    private fun setImagesListToImageSlider() {
        val imagesUrl = product.images
        val imageList = mutableListOf<SlideModel>()
        imagesUrl?.forEach {
            imageList.add(SlideModel(it))
        }

        viewBinding.imageSlider.setImageList(imageList)
    }

//
//    companion object {
//        fun getInstance(product: Product): ProductDetailsFragment {
//            val instance = ProductDetailsFragment()
//            instance.product = product
//            return instance
//        }
//    }

}