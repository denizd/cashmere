package com.denizd.cashmere.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Outfit(
    val name: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "outfit_id") val outfitId: Int
)