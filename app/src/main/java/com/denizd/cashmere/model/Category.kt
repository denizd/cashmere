package com.denizd.cashmere.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val name: String,
    @ColumnInfo(name = "drawable_name") val drawableName: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "category_id") val categoryId: Long
)