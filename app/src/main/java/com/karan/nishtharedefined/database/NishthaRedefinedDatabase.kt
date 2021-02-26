package com.karan.nishtharedefined.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.database.dao.ContactDao
import com.karan.nishtharedefined.database.dao.NishthaOnlineLanguageDao
import com.karan.nishtharedefined.database.dao.NishthaOnlineModuleDao
import com.karan.nishtharedefined.database.dao.NishthaOnlineModuleDetailDao
import com.karan.nishtharedefined.database.dataobjects.Contact
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineLanguageDatabaseObject
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineModuleDatabaseObject
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineModuleDetailDatabaseObject


@Database(
    entities = [NishthaOnlineLanguageDatabaseObject::class,
        NishthaOnlineModuleDatabaseObject::class,
        NishthaOnlineModuleDetailDatabaseObject::class,
        Contact::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val nishthaOnlineLanguageDao: NishthaOnlineLanguageDao
    abstract val nishthaOnlineModuleDao: NishthaOnlineModuleDao
    abstract val nishthaOnlineModuleDetailDao: NishthaOnlineModuleDetailDao
    abstract val contactDao : ContactDao
}