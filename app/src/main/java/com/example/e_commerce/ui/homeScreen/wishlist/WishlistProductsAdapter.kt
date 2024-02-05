package com.example.e_commerce.ui.homeScreen.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.WishlistProductItemBinding


class WishlistProductsAdapter(var products:List<Product?>?):RecyclerView.Adapter<WishlistProductsAdapter.WishlistProductsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistProductsViewHolder {
        val viewBinding = WishlistProductItemBinding.inflate(
            LayoutInflater.from(parent.context) , parent, false
        )
        return WishlistProductsViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return products?.size?:0
    }

    override fun onBindViewHolder(holder: WishlistProductsViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product)
        holder.viewBinding.delete.setOnClickListener{
            onRemoveFromWishlistClickListener.onProductClick(product)
        }
    }

    fun setData(products:List<Product?>?){
        this.products = products
        notifyDataSetChanged()
    }
    class WishlistProductsViewHolder(val viewBinding: WishlistProductItemBinding):ViewHolder(viewBinding.root) {
        fun bind(product: Product?) {
            viewBinding.product = product
        }
    }

    lateinit var onRemoveFromWishlistClickListener: OnProductClickListener
    fun interface OnProductClickListener{
        fun onProductClick(product: Product?)
    }
}

