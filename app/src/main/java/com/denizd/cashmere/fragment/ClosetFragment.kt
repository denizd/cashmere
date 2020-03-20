package com.denizd.cashmere.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.denizd.cashmere.R
import com.denizd.cashmere.adapter.ClothingAdapter
import com.denizd.cashmere.databinding.FragmentClosetBinding
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.model.Clothing
import com.denizd.cashmere.sheet.AddClothingSheet
import com.denizd.cashmere.util.viewBinding
import com.denizd.cashmere.viewmodel.ClosetViewModel

class ClosetFragment : BaseFragment(R.layout.fragment_closet) {

    private val binding: FragmentClosetBinding by viewBinding(FragmentClosetBinding::bind)
    private val viewModel: ClosetViewModel by viewModels()
    private val clothingAdapter = ClothingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = clothingAdapter
            layoutManager = StaggeredGridLayoutManager(
                getGridColumnCount(resources.configuration, 2) + 2,
                StaggeredGridLayoutManager.VERTICAL)
        }
        viewModel.allClothing.observe(viewLifecycleOwner, Observer { clothing ->
            clothingAdapter.submitList(clothing)
        })
        binding.fab.apply {
            addFabScrollListener(binding.recyclerView)
            setOnClickListener {
                openBottomSheet(AddClothingSheet())
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        (binding.recyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = getGridColumnCount(newConfig) + 2
    }

    fun getAllCategories(): LiveData<List<Category>> = viewModel.allCategories
    fun insertClothing(clothing: Clothing) { viewModel.insertClothing(clothing) }
}