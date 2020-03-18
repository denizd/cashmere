package com.denizd.cashmere.sheet

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.denizd.cashmere.R
import com.denizd.cashmere.adapter.CategoryAdapter
import com.denizd.cashmere.databinding.SheetCategoryBinding
import com.denizd.cashmere.fragment.SettingsFragment
import com.denizd.cashmere.util.viewBinding

class CategorySheet : BaseSheet(R.layout.sheet_category) {

    private val binding: SheetCategoryBinding by viewBinding(SheetCategoryBinding::bind)
    private lateinit var settingsFragment: SettingsFragment
    private val categoryAdapter = CategoryAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsFragment = targetFragment as SettingsFragment

        binding.recyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context)
        }
        settingsFragment.getAllCategories().observe(viewLifecycleOwner, Observer { categories ->
            categoryAdapter.setCategories(categories)
        })

        binding.addButton.setOnClickListener {
            openBottomSheet(AddCategorySheet(), settingsFragment)
        }
    }
}