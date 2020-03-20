package com.denizd.cashmere.sheet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.denizd.cashmere.R
import com.denizd.cashmere.adapter.DrawableAdapter
import com.denizd.cashmere.databinding.SheetAddCategoryBinding
import com.denizd.cashmere.fragment.SettingsFragment
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.util.trimmed
import com.denizd.cashmere.util.viewBinding

class AddCategorySheet : BaseSheet(R.layout.sheet_add_category) {

    private val binding: SheetAddCategoryBinding by viewBinding(SheetAddCategoryBinding::bind)
    private val drawableAdapter = DrawableAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = drawableAdapter
            layoutManager = GridLayoutManager(context, 4)
        }

        binding.cancelButton.setOnClickListener { dismiss() }
        binding.addButton.setOnClickListener {
            if (binding.categoryNameTextInput.text.isNullOrEmpty()) {
                binding.categoryNameTextInput.error = getString(R.string.no_name_entered)
                return@setOnClickListener
            }
            if (!drawableAdapter.hasItemBeenSelected) {
                binding.categoryNameTextInput.error = getString(R.string.no_icon_selected)
                return@setOnClickListener
            }
            (targetFragment as SettingsFragment).insertCategory(
                Category(
                    binding.categoryNameTextInput.trimmed(),
                    drawableAdapter.selectedItemName,
                    0L
                )
            )
            dismiss()
        }
    }
}