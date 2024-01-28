package com.example.e_commerce.ui.homeScreen.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.cart.addToCart.CartProduct
import com.example.domain.model.cart.loggedCart.ProductsItem
import com.example.e_commerce.databinding.CartProductItemBinding

class CartProductAdapter(var products: List<ProductsItem?>? = null) :
    Adapter<CartProductAdapter.CartProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val viewBinding = CartProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartProductViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product)
        holder.viewBinding.delete.setOnClickListener{
            onCartProductDeleteClickListener.onProductClick(productId = product?.id?:"")
        }
    }

    override fun getItemCount(): Int = products?.size ?: 0

    fun setData(products: List<ProductsItem?>?) {
        this.products = products
        notifyDataSetChanged()
    }

    class CartProductViewHolder(val viewBinding: CartProductItemBinding) :
        ViewHolder(viewBinding.root) {
        fun bind(productItem: ProductsItem?) {
            viewBinding.product = productItem
        }
    }
    lateinit var onCartProductDeleteClickListener: OnCartProductClickListener
    fun interface OnCartProductClickListener{
        fun onProductClick(productId:String)
    }
}