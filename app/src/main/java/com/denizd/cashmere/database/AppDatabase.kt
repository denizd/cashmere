package com.denizd.cashmere.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denizd.cashmere.model.Category
import com.denizd.cashmere.model.Clothing
import com.denizd.cashmere.model.ClothingOutfitRelation
import com.denizd.cashmere.model.Outfit

@Database(entities = [Category::class, Clothing::class, ClothingOutfitRelation::class, Outfit::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): AppDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cashmere_db"
                ).build()
            }
            return instance!!
        }
    }
}