package com.example.e_commerce.ui.homeScreen.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.e_commerce.databinding.CategoryItemBinding
import com.example.domain.model.category.Category

class CategoriesAdapter(private var categories:List<Category?>? =null) :Adapter<CategoriesAdapter.CategoriesViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val viewBinding:CategoryItemBinding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoriesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories!![position]
        holder.bind(category)
        holder.viewBinding.root.setOnClickListener{
            onCategoryClickListener.onItemClick(category,position)
        }
    }

    override fun getItemCount(): Int {
        return categories?.size?:0
    }

    fun bind(categoriesList: List<Category?>?) {
        categories = categoriesList
        notifyDataSetChanged()
    }

    class CategoriesViewHolder( val viewBinding:CategoryItemBinding):ViewHolder(viewBinding.root){
        fun bind(category : Category? ){
            viewBinding.category = category
        }
    }

    lateinit var onCategoryClickListener:OnItemClickListener

    fun interface OnItemClickListener{
        fun onItemClick(item: Category?, position: Int)
    }
}