package com.example.e_commerce.ui.homeScreen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        viewBinding = FragmentHomeBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.invokeAction(HomeCategoriesContract.Action.LoadCategories)
        viewModel.invokeAction(HomeCategoriesContract.Action.LoadProducts())
        observeOnLiveData()
        initViews()
    }

    private lateinit var homeCategoriesAdapter: HomeCategoriesAdapter
    private lateinit var homeProductsAdapter: HomeProductsAdapter
    private fun initViews() {
        homeCategoriesAdapter = HomeCategoriesAdapter()
        viewBinding.homeCategoriesRecycler.adapter = homeCategoriesAdapter
        viewBinding.homeCategoriesRecycler.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        homeProductsAdapter = HomeProductsAdapter(null)
        viewBinding.homeProductsRecycler.adapter = homeProductsAdapter
        viewBinding.homeProductsRecycler.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL, false
        )

        homeCategoriesAdapter.onCategoryClickListener =
            HomeCategoriesAdapter.OnItemClickListener { category, _ ->
                val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment(category!!)
                findNavController().navigate(action)
            }

        homeProductsAdapter.onHomeProductClickListener =
            HomeProductsAdapter.OnHomeProductClickListener { product ->
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
                findNavController().navigate(action)
            }

        viewBinding.viewAllTxt.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment()
            findNavController().navigate(action)
        }

        setImageListToSlider()
    }

    private fun observeOnLiveData() {
        viewModel.event.observe(viewLifecycleOwner) {
            handleEvents(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            renderStates(it)
        }
    }

    private fun renderStates(state: HomeCategoriesContract.State) {
        when (state) {
            is HomeCategoriesContract.State.FailedState -> {
                handleFailed()
            }

            is HomeCategoriesContract.State.LoadingState -> {
                handleLoading()
            }

            is HomeCategoriesContract.State.CategoriesSuccessState -> {
                handleSuccess(state.categories, null)
            }

            is HomeCategoriesContract.State.ProductsSuccessState -> {
                handleSuccess(null, state.products)
            }

        }
    }

    private fun handleSuccess(categories: List<Category?>?, products: List<Product?>?) {

        if (categories != null) {
            homeCategoriesAdapter.setData(categories)
        } else {
            homeProductsAdapter.setData(products)
            viewBinding.product = products!![0]
        }
        viewBinding.successView.isVisible = true
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = false
    }

    private fun handleLoading() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = true
        viewBinding.failedView.isVisible = false
    }

    private fun handleFailed() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = true
    }

    private fun handleEvents(event: HomeCategoriesContract.Event) {
        when (event) {
            is HomeCategoriesContract.Event.NavigateToAllCategories -> {
            }

            is HomeCategoriesContract.Event.NavigateToProduct -> {
            }
        }
    }

    private lateinit var imageList: MutableList<SlideModel>
    private fun setImageListToSlider() {
        imageList = mutableListOf()
        imageList.add(SlideModel(R.drawable.offers_image))
        imageList.add(SlideModel(R.drawable.mens_fashion))
        imageList.add(SlideModel(R.drawable.womens_fashion))

        viewBinding.imageSlider.setImageList(imageList)
    }

//    lateinit var onCategoryClickListener: OnCategoryClickListener
//
//    fun interface OnCategoryClickListener {
//        fun onCategoryClick(category: Category?)
//    }
}