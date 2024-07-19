package com.example.e_commerce.ui.homeScreen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.model.category.Category
import com.example.domain.model.product.Product
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentHomeBinding
import com.example.e_commerce.ui.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private lateinit var viewModel: HomeFragmentViewModel
    private val homeCategoriesAdapter: HomeCategoriesAdapter by lazy {
        HomeCategoriesAdapter(null)
    }
    private val homeProductsAdapter: HomeProductsAdapter by lazy {
        HomeProductsAdapter(null)
    }

    lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = TokenManager(requireContext()).getToken()!!
        viewModel.invokeAction(HomeFragmentContract.Action.LoadCategories)
        viewModel.invokeAction(HomeFragmentContract.Action.LoadProducts(token = token))
        observeOnLiveData()
        initViews()
    }


    private fun initViews() {
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this

        handelAdapters()

        setImageListToSlider()
    }

    private fun handelAdapters() {
        binding.homeCategoriesRecycler.adapter = homeCategoriesAdapter
        binding.homeCategoriesRecycler.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        binding.homeProductsRecycler.adapter = homeProductsAdapter
        binding.homeProductsRecycler.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL, false
        )

        homeCategoriesAdapter.onCategoryClickListener =
            HomeCategoriesAdapter.OnItemClickListener { category, _ ->
                viewModel.event.postValue(
                    HomeFragmentContract.Event.NavigateToProductsScreen(category!!)
                )
            }

        homeProductsAdapter.onHomeProductClickListener =
            HomeProductsAdapter.OnHomeProductClickListener { product ->
                viewModel.event.postValue(
                    HomeFragmentContract.Event.NavigateToProductDetails(product)
                )
            }

        homeProductsAdapter.onAddOrRemoveProductToWishlistClickListener =
            HomeProductsAdapter.OnHomeProductClickListener { product ->
                if (product.inWishlist) {
                    viewModel.invokeAction(
                        HomeFragmentContract.Action.RemoveProductFromWishlist(
                            productId = product.id ?: "", token = token
                        )
                    )
                } else {
                    viewModel.invokeAction(
                        HomeFragmentContract.Action.AddProductToWishlist(
                            token = this.token, productId = product.id ?: ""
                        )
                    )
                }


            }
    }

    private fun observeOnLiveData() {
        viewModel.event.observe(viewLifecycleOwner) {
            handleEvents(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            renderStates(it)
        }
    }

    private fun renderStates(state: HomeFragmentContract.State) {
        when (state) {
            is HomeFragmentContract.State.FailedState -> {
                handleFailed()
            }

            is HomeFragmentContract.State.LoadingState -> {
                handleLoading()
            }

            is HomeFragmentContract.State.CategoriesSuccessState -> {
                handleSuccess(state.categories, null)
            }

            is HomeFragmentContract.State.ProductsSuccessState -> {
                handleSuccess(null, state.products)
            }

            HomeFragmentContract.State.AddingProductToWishlistFailed -> {
                showToastMessage(message = "Failed To Add Product")
            }

            HomeFragmentContract.State.ProductAddedToWishlistSuccessfully -> {
                showToastMessage(message = "Product Added Successfully")

            }

            HomeFragmentContract.State.ProductRemovedFromWishlistSuccessfully -> {
                showToastMessage(message = "Product Removed Successfully")

            }
            HomeFragmentContract.State.RemovingProductFromWishlistFailed -> {
                showToastMessage(message = "Failed To Remove Product")
            }
        }
    }

    private fun showToastMessage(message:String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    private fun handleSuccess(categories: List<Category?>?, products: List<Product?>?) {

        if (categories != null) {
            homeCategoriesAdapter.setData(categories)
        } else {
            homeProductsAdapter.setData(products)
            binding.product = products!![0]
        }
        binding.successView.isVisible = true
        binding.loadingView.isVisible = false
        binding.failedView.isVisible = false
    }

    private fun handleLoading() {
        binding.successView.isVisible = false
        binding.loadingView.isVisible = true
        binding.failedView.isVisible = false
    }

    private fun handleFailed() {
        binding.successView.isVisible = false
        binding.loadingView.isVisible = false
        binding.failedView.isVisible = true
    }

    private fun handleEvents(event: HomeFragmentContract.Event) {
        when (event) {
            is HomeFragmentContract.Event.NavigateToCategoriesScreen -> {
                navigateToCategories()
            }

            is HomeFragmentContract.Event.NavigateToProductDetails -> {
                navigateToProductDetails(event.product)
            }

            is HomeFragmentContract.Event.NavigateToProductsScreen -> {
                navigateToProducts(event.category)
            }
        }
    }

    private fun navigateToProducts(category: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment(category)
        findNavController().navigate(action)
    }

    private fun navigateToProductDetails(product: Product) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
        findNavController().navigate(action)
    }


    private fun navigateToCategories() {
        val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment()
        findNavController().navigate(action)
    }

    private lateinit var imageList: MutableList<SlideModel>
    private fun setImageListToSlider() {
        imageList = mutableListOf()
        imageList.add(SlideModel(R.drawable.offers_image))
        imageList.add(SlideModel(R.drawable.mens_fashion))
        imageList.add(SlideModel(R.drawable.womens_fashion))

        binding.imageSlider.setImageList(imageList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

}