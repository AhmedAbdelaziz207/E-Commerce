package com.example.e_commerce.ui.homeScreen.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.HomeProductItemBinding
import com.example.e_commerce.ui.homeScreen.products.ProductsFragment

class HomeProductsAdapter(private var products: List<Product?>?) :
    RecyclerView.Adapter<HomeProductsAdapter.HomeProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsViewHolder {
        val viewBinding = HomeProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HomeProductsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: HomeProductsViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product!!)

        holder.viewBinding.root.setOnClickListener {
            onHomeProductClickListener.onProductClick(product)
        }
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    fun setData(products: List<Product?>?) {
        this.products = products
        notifyDataSetChanged()
    }

    class HomeProductsViewHolder( val viewBinding: HomeProductItemBinding) :
        ViewHolder(viewBinding.root) {
        fun bind(product: Product) {
            viewBinding.product = product
        }
    }
    lateinit var onHomeProductClickListener: OnHomeProductClickListener


   fun interface OnHomeProductClickListener{
        fun onProductClick(product: Product)
    }
}