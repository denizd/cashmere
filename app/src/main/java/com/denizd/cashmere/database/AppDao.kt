package com.denizd.cashmere.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.model.Clothing

@Dao
interface AppDao {

    @get:Query("SELECT * FROM category ORDER BY category_id")
    val allCategories: LiveData<List<Category>>

    @get:Query("SELECT * FROM clothing ORDER BY clothing_id")
    val allClothing: DataSource.Factory<Int, Clothing>

    @Insert
    fun insertCategory(category: Category): Long

    @Insert
    fun insertClothing(clothing: Clothing): Long
}