package com.karan.nishtharedefined.database

import android.content.Context
import androidx.room.Room

class NishthaRedefinedDatabaseBuilder {

    private lateinit var INSTANCE: NishthaRedefinedDatabase

    fun getDatabase(context: Context): NishthaRedefinedDatabase {
        synchronized(NishthaRedefinedDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, NishthaRedefinedDatabase::class.java, "nishtha.db"
                ).build()
            }
        }
        return INSTANCE
    }

}