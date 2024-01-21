package com.example.e_commerce.ui.homeScreen.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.product.Product
import com.example.e_commerce.databinding.HomeProductItemBinding

class ProductsAdapter(private var products : List<Product?>?):RecyclerView.Adapter<ProductsAdapter.HomeProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsViewHolder {
        val viewBinding = HomeProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,false
        )

        return HomeProductsViewHolder(viewBinding)
    }
    override fun onBindViewHolder(holder: HomeProductsViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product!!)
        holder.viewBinding.root.setOnClickListener{
            onProductClickListener.onProductClick(product)
        }
    }

    override fun getItemCount(): Int {
        return products?.size?:0
    }

    fun setData(products: List<Product?>?) {
        this.products = products
        notifyDataSetChanged()
    }


    class HomeProductsViewHolder( val viewBinding:HomeProductItemBinding):ViewHolder(viewBinding.root) {
        fun bind(product: Product) {
            viewBinding.product = product
        }
    }
    lateinit var onProductClickListener: OnProductClickListener
    fun interface OnProductClickListener{
        fun onProductClick(product: Product)
    }
}