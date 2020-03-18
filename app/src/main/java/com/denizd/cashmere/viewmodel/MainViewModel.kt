package com.denizd.cashmere.viewmodel

class MainViewModel : BaseViewModel() {

    fun getAppTheme(): Int = repo.getAppTheme()
}