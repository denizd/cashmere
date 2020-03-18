package com.denizd.cashmere.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denizd.cashmere.databinding.ItemClothingDrawableBinding
import com.denizd.cashmere.util.ClothingDrawable

class DrawableAdapter : RecyclerView.Adapter<DrawableAdapter.DrawableViewHolder>() {

    var selectedItemPosition: Int = -1
    var selectedItemName: String = ""

    class DrawableViewHolder(val binding: ItemClothingDrawableBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var selectedItemPosition: Int = -1
        var selectedItemName: String = ""

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedItemPosition = 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawableViewHolder =
        DrawableViewHolder(ItemClothingDrawableBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DrawableViewHolder, position: Int) {
        ClothingDrawable.values().forEach { currentItem ->
            holder.binding.apply {
                if (selectedItemPosition == position) {

                } else {

                }
            }
        }
    }

    override fun getItemCount(): Int = ClothingDrawable.values().size
}