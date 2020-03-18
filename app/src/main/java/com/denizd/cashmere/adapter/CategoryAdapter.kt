package com.denizd.cashmere.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denizd.cashmere.databinding.ItemCategoryBinding
import com.denizd.cashmere.model.Category

class CategoryAdapter(private var categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.apply {
                setOnClickListener(this@CategoryViewHolder)
                setOnLongClickListener(this@CategoryViewHolder)
            }
        }

        override fun onClick(v: View?) {

        }

        override fun onLongClick(v: View?): Boolean {

            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories[position].also { currentItem ->
            holder.binding.apply {

            }
        }
    }

    override fun getItemCount(): Int = categories.size

    fun setCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}