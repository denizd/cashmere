package com.denizd.cashmere.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denizd.cashmere.model.Category

@Dao
interface AppDao {

    @get:Query("SELECT * FROM category ORDER BY name")
    val allCategories: LiveData<List<Category>>



    @Insert
    fun insertCategory(category: Category): Long
}