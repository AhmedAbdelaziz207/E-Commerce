package com.example.e_commerce.ui.homeScreen.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.model.category.Category
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var viewBinding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoriesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.invokeAction(CategoriesContract.Action.LoadCategories)

        initViews()
        observeOnLiveData()
    }

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var sideMenuCategoriesAdapter: SideMenuCategoriesAdapter
    private lateinit var imageList: MutableList<SlideModel>


    private fun initViews() {
        sideMenuCategoriesAdapter = SideMenuCategoriesAdapter()
        viewBinding.sideMenuCategoriesRecycler.adapter = sideMenuCategoriesAdapter
        categoriesAdapter = CategoriesAdapter()
        viewBinding.categoriesRecycler.adapter = categoriesAdapter


        categoriesAdapter.onCategoryClickListener =
            CategoriesAdapter.OnItemClickListener { category, _ ->
                val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(category!!)
                findNavController().navigate(action)
            // onCategoryClickListener.onCategoryClick(category)
            }

        sideMenuCategoriesAdapter.onCategoryClickListener =
            SideMenuCategoriesAdapter.OnItemClickListener{category, _ ->
                val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(category!!)
                findNavController().navigate(action)
//                onSideMenuCategoryClickListener.onCategoryClick(category)
            }

            setImageListToSlider()
    }

    private fun setImageListToSlider() {
        imageList = mutableListOf()
        imageList.add(SlideModel(R.drawable.mens_fashion))
        imageList.add(SlideModel(R.drawable.womens_fashion))
        viewBinding.imageSlider.startSliding(9000) // with new period
        viewBinding.imageSlider.startSliding()
        viewBinding.imageSlider.stopSliding()
        viewBinding.imageSlider.setImageList(imageList)
    }


    private fun observeOnLiveData() {
        viewModel.event.observe(viewLifecycleOwner) { handleEvents(it) }

        viewModel.state.observe(viewLifecycleOwner) { renderStates(it) }
    }

    private fun renderStates(states: CategoriesContract.State) {
        when (states) {
            is CategoriesContract.State.LoadingState -> {
                showLoadingView(states.message)
            }

            is CategoriesContract.State.SuccessState -> {
                showSuccessView(states.categories)
            }

            is CategoriesContract.State.FailedState -> {
                showFailedView(states.message)
            }
        }
    }

    private fun handleEvents(event: CategoriesContract.Event) {
        when (event) {
            is CategoriesContract.Event.NavigateToCategoriesDetails -> {
            }
        }
    }

    private fun showFailedView(message: String) {
        viewBinding.loadingView.isVisible = false
        viewBinding.successView.isVisible = false
        viewBinding.failedView.isVisible = true
    }

    private fun showSuccessView(categories: List<Category?>?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = true
        categoriesAdapter.bind(categories)
        sideMenuCategoriesAdapter.bind(categories)
    }

    private fun showLoadingView(message: String) {
        viewBinding.loadingView.isVisible = true
        viewBinding.failedView.isVisible = false
        viewBinding.successView.isVisible = false
    }

    // callback to return to home and change fragment
//    lateinit var onCategoryClickListener: OnCategoryClickListener
//    lateinit var onSideMenuCategoryClickListener: OnCategoryClickListener
//
//    fun interface OnCategoryClickListener {
//        fun onCategoryClick(category: Category?)
//    }

}
