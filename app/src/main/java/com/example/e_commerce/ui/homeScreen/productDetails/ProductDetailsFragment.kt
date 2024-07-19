package com.example.e_commerce.ui.homeScreen.productDetails

import android.os.Bundle
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
    private var _binding :FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding get() = _binding!!
    lateinit var viewModel: ProductDetailsViewModel
    lateinit var product: Product
    private val args: ProductDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
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

        binding.product = product
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel
        binding.action = ProductDetailsContract.Action.AddProductToCart(product)


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

        binding.imageSlider.setImageList(imageList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

}