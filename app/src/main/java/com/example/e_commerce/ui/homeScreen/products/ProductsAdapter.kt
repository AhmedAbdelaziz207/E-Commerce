package com.example.e_commerce.ui.homeScreen.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.product.Product
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ProductItemBinding

class ProductsAdapter(private var products : List<Product?>?):RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val viewBinding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,false
        )

        return ProductsViewHolder(viewBinding)
    }
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product!!)

        if (product.inWishlist){
            holder.viewBinding.addToWashlist.setImageResource(R.drawable.ic_added_to_wishlist)
        }else{
            holder.viewBinding.addToWashlist.setImageResource(R.drawable.ic_add_to_wishlist)
        }

        holder.viewBinding.root.setOnClickListener{
            onProductClickListener.onProductClick(product , position)
        }

        holder.viewBinding.addToWashlist.setOnClickListener{
            onAddOrRemoveProductToWishlistClickListener.onProductClick(product, position)
        }
    }


    override fun getItemCount(): Int {
        return products?.size?:0
    }

    fun setData(products: List<Product?>?) {
        this.products = products
        notifyDataSetChanged()
    }


    class ProductsViewHolder( val viewBinding: ProductItemBinding):ViewHolder(viewBinding.root) {
        fun bind(product: Product) {
            viewBinding.product = product
        }
    }
    lateinit var onProductClickListener: OnProductClickListener
    lateinit var onAddOrRemoveProductToWishlistClickListener: OnProductClickListener
    fun interface OnProductClickListener{
        fun onProductClick(product: Product,position: Int)
    }
}