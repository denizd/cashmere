package com.denizd.cashmere.database

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.denizd.cashmere.model.Category

class AppRepository private constructor(context: Context) {

    companion object {
        lateinit var instance: AppRepository
        fun init(context: Context) {
            if (!::instance.isInitialized) instance = AppRepository(context)
        }
    }

    private val dao: AppDao = AppDatabase.getInstance(context.applicationContext).dao()
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    // Preferences
    fun getAppTheme(): Int = prefs.getInt("app_theme", 2)
    fun setAppTheme(value: Int) { prefs.edit().putInt("app_theme", value).apply() }

    // Database
    // get
    val allCategories: LiveData<List<Category>> = dao.allCategories

    // Insert
    fun insertCategory(category: Category) { dao.insertCategory(category) }
}