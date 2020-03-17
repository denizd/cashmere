package com.denizd.cashmere.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denizd.cashmere.database.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected val repo: AppRepository = AppRepository.instance
    fun doAsync(function: () -> Unit) = viewModelScope.launch(Dispatchers.IO) { function.invoke() }
}