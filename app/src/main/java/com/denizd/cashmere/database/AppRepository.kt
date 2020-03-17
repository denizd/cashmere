package com.denizd.cashmere.database

import android.content.Context

class AppRepository private constructor(context: Context) {

    companion object {
        lateinit var instance: AppRepository
        fun init(context: Context) {
            if (::instance.isInitialized) instance = AppRepository(context)
        }
    }

    private val dao: AppDao = AppDatabase.getInstance(context).dao()
}