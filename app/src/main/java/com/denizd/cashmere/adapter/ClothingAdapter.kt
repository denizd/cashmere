package com.denizd.cashmere.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denizd.cashmere.databinding.ItemClothingBinding
import com.denizd.cashmere.model.Clothing
import com.squareup.picasso.Picasso

class ClothingAdapter : PagedListAdapter<Clothing, ClothingAdapter.ClothingViewHolder>(DIFF_CALLBACK) {

    class ClothingViewHolder(val binding: ItemClothingBinding) : RecyclerView.ViewHolder(binding.root)
    // TODO OnClickListener for imageView (view image larger) and linearLayout (edit sheet)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothingViewHolder =
        ClothingViewHolder(ItemClothingBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ClothingViewHolder, position: Int) {
        getItem(position)?.let { clothing ->
            holder.binding.apply {
                imageView.visibility = if (clothing.imageUri.isNotBlank()) {
                    Picasso.get().load(clothing.imageUri).into(imageView)
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Clothing> = object : DiffUtil.ItemCallback<Clothing>() {
            override fun areItemsTheSame(oldClothing: Clothing, newClothing: Clothing) =
                oldClothing.clothingId == newClothing.clothingId
            override fun areContentsTheSame(oldClothing: Clothing, newClothing: Clothing) =
                oldClothing == newClothing
        }
    }
}