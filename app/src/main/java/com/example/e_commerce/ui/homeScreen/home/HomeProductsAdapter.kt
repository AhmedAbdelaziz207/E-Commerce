package com.example.e_commerce.ui.homeScreen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.product.Product
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ProductItemBinding

class HomeProductsAdapter(private var products: List<Product?>?) :
    RecyclerView.Adapter<HomeProductsAdapter.HomeProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsViewHolder {
        val viewBinding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HomeProductsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: HomeProductsViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product!!)

        if (product.inWishlist) {
            holder.viewBinding.addToWashlist.setImageResource(R.drawable.ic_added_to_wishlist)
        } else {
            holder.viewBinding.addToWashlist.setImageResource(R.drawable.ic_add_to_wishlist)
        }

        holder.viewBinding.root.setOnClickListener {
            onHomeProductClickListener.onProductClick(product)
        }
        holder.viewBinding.addToWashlist.setOnClickListener {
            onAddOrRemoveProductToWishlistClickListener.onProductClick(product)
        }

    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    fun setData(products: List<Product?>?) {
        this.products = products
        notifyDataSetChanged()
    }

    class HomeProductsViewHolder(val viewBinding: ProductItemBinding) :
        ViewHolder(viewBinding.root) {
        fun bind(product: Product) {
            viewBinding.product = product
        }
    }

    lateinit var onHomeProductClickListener: OnHomeProductClickListener
    lateinit var onAddOrRemoveProductToWishlistClickListener: OnHomeProductClickListener


    fun interface OnHomeProductClickListener {
        fun onProductClick(product: Product)
    }
}