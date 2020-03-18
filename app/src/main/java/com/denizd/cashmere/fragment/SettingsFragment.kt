package com.denizd.cashmere.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.FragmentSettingsBinding
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.sheet.CategorySheet
import com.denizd.cashmere.util.viewBinding
import com.denizd.cashmere.viewmodel.SettingsViewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewCategoriesButton.setOnClickListener {
            openBottomSheet(CategorySheet())
        }
    }

    fun getAllCategories(): LiveData<List<Category>> = viewModel.allCategories
    fun insertCategory(category: Category) { viewModel.insertCategory(category) }
}