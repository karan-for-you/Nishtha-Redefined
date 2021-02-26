package com.karan.nishtharedefined.db

import android.content.Context
import androidx.room.Room

class NishthaRedefinedDatabaseBuilder {
    private lateinit var nishthaRedefinedDatabaseInstance: NishthaRedefinedDatabase

    fun getDatabase(context: Context): NishthaRedefinedDatabase {
        synchronized(NishthaRedefinedDatabase::class.java) {
            if (!::nishthaRedefinedDatabaseInstance.isInitialized) {
                nishthaRedefinedDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext, NishthaRedefinedDatabase::class.java,
                    "nishthaRedefined.db"
                ).build()
            }
        }
        return nishthaRedefinedDatabaseInstance
    }
}