package com.example.e_commerce.ui.homeScreen.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.category.Category
import com.example.e_commerce.databinding.SideMenuCategoryItemBinding

class SideMenuCategoriesAdapter(private var sideCategories:List<Category?>? = null) :Adapter<SideMenuCategoriesAdapter.SidMenuCategoriesViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SidMenuCategoriesViewHolder {
        val viewBinding:SideMenuCategoryItemBinding = SideMenuCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SidMenuCategoriesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SidMenuCategoriesViewHolder, position: Int) {
        val category = sideCategories!![position]
        holder.bind(category)
        holder.viewBinding.root.setOnClickListener{
            onCategoryClickListener.onItemClick(category,position)
        }
    }

    override fun getItemCount(): Int {
        return sideCategories?.size?:0
    }

    class SidMenuCategoriesViewHolder( val viewBinding:SideMenuCategoryItemBinding):ViewHolder(viewBinding.root){
        fun bind(category : Category? ){
            viewBinding.category = category
            viewBinding.executePendingBindings()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bind(categoriesList: List<Category?>?) {
        sideCategories = categoriesList
        notifyDataSetChanged()
    }
    lateinit var onCategoryClickListener:OnItemClickListener

    fun interface OnItemClickListener{
        fun onItemClick(item: Category?, position: Int)
    }
}