package com.denizd.cashmere.sheet

import android.os.Bundle
import android.view.View
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.SheetAddCategoryBinding
import com.denizd.cashmere.fragment.SettingsFragment
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.util.viewBinding

class AddCategorySheet : BaseSheet(R.layout.sheet_add_category) {

    private val binding: SheetAddCategoryBinding by viewBinding(SheetAddCategoryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener { dismiss() }
        binding.addButton.setOnClickListener {
            (targetFragment as SettingsFragment).insertCategory(
                Category(
                    binding.categoryNameTextInput.text.toString(),
                    TODO("store selected item in adapter"),
                    0L
                )
            )
            dismiss()
        }
    }
}