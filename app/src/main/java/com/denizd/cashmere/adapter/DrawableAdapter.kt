package com.denizd.cashmere.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.ItemClothingDrawableBinding
import com.denizd.cashmere.util.ClothingDrawable

class DrawableAdapter : RecyclerView.Adapter<DrawableAdapter.DrawableViewHolder>(), ClothingDrawableClickListener {

    private val drawables: Array<ClothingDrawable> = ClothingDrawable.values()
    private var selectedItemPosition: Int = -1
    var selectedItemName: String = ""
    val hasItemBeenSelected: Boolean get() = selectedItemPosition != -1

    class DrawableViewHolder(
        val binding: ItemClothingDrawableBinding,
        private val onClickListener: ClothingDrawableClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var itemPosition: Int = 0
        var itemName: String = ""

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onDrawableClick(itemPosition, itemName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawableViewHolder =
        DrawableViewHolder(
            ItemClothingDrawableBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            this
        )

    override fun onBindViewHolder(holder: DrawableViewHolder, position: Int) {
        drawables[position].also { currentItem ->
            holder.binding.apply {
                imageView.setImageDrawable(imageView.context.getDrawable(currentItem.drawableId))
                backgroundView.setBackgroundColor(backgroundView.context.getColor(if (selectedItemPosition == position) {
                    R.color.colorAccent
                } else {
                    android.R.color.transparent
                }))
                imageView.setColorFilter(imageView.context.getColor(if (selectedItemPosition == position) {
                    R.color.colorOnDarkContrast
                } else {
                    R.color.colorOnLight
                }))
            }
            holder.itemPosition = position
            holder.itemName = currentItem.name
        }
    }

    override fun getItemCount(): Int = drawables.size

    override fun onDrawableClick(position: Int, name: String) {
        notifyItemChanged(selectedItemPosition) // refreshes previously selected item
        selectedItemPosition = position
        selectedItemName = name
        notifyItemChanged(position) // refreshes newly selected item
    }
}

interface ClothingDrawableClickListener {
    fun onDrawableClick(position: Int, name: String)
}