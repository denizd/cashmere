package com.denizd.cashmere.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.model.Clothing

class ClosetViewModel : BaseViewModel() {

    val allCategories: LiveData<List<Category>> = repo.allCategories
    val allClothing: LiveData<PagedList<Clothing>> = repo.allClothing

    fun insertClothing(clothing: Clothing) = doAsync { repo.insertClothing(clothing) }
}