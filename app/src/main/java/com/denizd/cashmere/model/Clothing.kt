package com.denizd.cashmere.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// TODO temperature rating (perhaps as an enum similar to clothing drawables?)
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Clothing(
    val brand: String,
    val colour: String,
    val size: String,
    val note: String,
    val temperatureRating: String,
    @ColumnInfo(name = "image_uri") val imageUri: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "clothing_id") val clothingId: Long,
    @ColumnInfo(name = "category_id") val categoryId: Long
)