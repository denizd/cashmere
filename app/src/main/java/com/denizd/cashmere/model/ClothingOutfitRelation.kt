package com.denizd.cashmere.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["clothing_id", "outfit_id"],
    foreignKeys = [
        ForeignKey(
            entity = Clothing::class,
            parentColumns = ["clothing_id"],
            childColumns = ["clothing_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Outfit::class,
            parentColumns = ["outfit_id"],
            childColumns = ["outfit_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ClothingOutfitRelation(
    @ColumnInfo(name = "clothing_id") val clothingId: Long,
    @ColumnInfo(name = "outfit_id") val outfitId: Long
)