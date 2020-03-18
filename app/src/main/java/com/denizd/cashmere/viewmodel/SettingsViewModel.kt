package com.denizd.cashmere.viewmodel

import androidx.lifecycle.LiveData
import com.denizd.cashmere.model.Category

class SettingsViewModel : BaseViewModel() {

    val allCategories: LiveData<List<Category>> = repo.allCategories

    fun insertCategory(category: Category) = doAsync { repo.insertCategory(category) }
}