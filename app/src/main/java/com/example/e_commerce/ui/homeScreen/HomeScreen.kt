package com.example.e_commerce.ui.homeScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.AcitivityHomeBinding
import com.example.e_commerce.ui.homeScreen.categories.CategoriesFragment
import com.example.e_commerce.ui.homeScreen.home.HomeFragment
import com.example.e_commerce.ui.homeScreen.productDetails.ProductDetailsFragment
import com.example.e_commerce.ui.homeScreen.products.ProductsFragment
import com.example.e_commerce.ui.homeScreen.profile.ProfileFragment
import com.example.e_commerce.ui.homeScreen.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {
    private lateinit var viewBinding: AcitivityHomeBinding
    private lateinit var navController : NavController
//    private val categoriesFragment = CategoriesFragment()
//    private val homeFragment = HomeFragment()
//    private var productsFragment: ProductsFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = AcitivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()


        viewBinding.bottomNavbar.setupWithNavController(navController)

        initViews()

    }


    private fun initViews() {


//        categoriesFragment.onCategoryClickListener =
//            CategoriesFragment.OnCategoryClickListener { category ->
//                productsFragment = ProductsFragment.getInstance(category)
//                navigateToFragment(productsFragment!!)
//               // setProductDetailsNavigation(productsFragment!!)
//            }
//
//        categoriesFragment.onSideMenuCategoryClickListener =
//            CategoriesFragment.OnCategoryClickListener { category ->
//                productsFragment = ProductsFragment.getInstance(category)
//                navigateToFragment(productsFragment!!)
//               // setProductDetailsNavigation(productsFragment!!)
//            }
//
//        homeFragment.onCategoryClickListener = HomeFragment.OnCategoryClickListener { category ->
//            productsFragment = ProductsFragment.getInstance(category)
//       //     setProductDetailsNavigation(productsFragment!!)
//            navigateToFragment(productsFragment!!)
//
//        }


    }

//    private fun setProductDetailsNavigation (productsFragment: ProductsFragment) {
//        productsFragment.let {
//            it.onProductClickListener = ProductsFragment.OnProductClickListener { product ->
//                navigateToFragment(ProductDetailsFragment.getInstance(product))
//            }
//        }
//    }

//    private fun handleBottomAppbarNavigation() {
//        viewBinding.bottomNavbar.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
//                    navigateToFragment(homeFragment)
//                }
//
//                R.id.categories -> {
//                    navigateToFragment(categoriesFragment)
//
//                }
//
//                R.id.wishlist -> {
//                    navigateToFragment(WishlistFragment())
//
//                }
//
//                R.id.profile -> {
//                    navigateToFragment(ProfileFragment())
//                }
//            }
//            true
//        }
//        viewBinding.bottomNavbar.selectedItemId = R.id.home
//
//    }


    @SuppressLint("CommitTransaction")
    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()


    }


}