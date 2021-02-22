package com.karan.nishtharedefined.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.database.dao.NishthaOnlineLanguageDao
import com.karan.nishtharedefined.database.dao.NishthaOnlineModuleDao
import com.karan.nishtharedefined.database.dao.NishthaOnlineModuleDetailDao


@Database(
    entities = [NishthaOnlineLanguageDao::class,
        NishthaOnlineModuleDao::class,
        NishthaOnlineModuleDetailDao::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val nishthaOnlineLanguageDao: NishthaOnlineLanguageDao
    abstract val nishthaOnlineModuleDao: NishthaOnlineModuleDao
    abstract val nishthaOnlineModuleDetailDao: NishthaOnlineModuleDetailDao
}