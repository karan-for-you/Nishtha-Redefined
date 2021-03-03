package com.karan.nishtharedefined.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.db.dao.ContactsDao
import com.karan.nishtharedefined.db.dao.NishthaLanguageDao
import com.karan.nishtharedefined.db.dao.NishthaModuleDao
import com.karan.nishtharedefined.db.dao.NishthaModuleDetailDao
import com.karan.nishtharedefined.db.dataobjects.Contact
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleModel

@Database(
    entities = [
        Contact::class,
        NishthaLanguageDao::class,
        NishthaModuleModel::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val nishthaLanguageDao: NishthaLanguageDao
    abstract val nishthaModuleDao: NishthaModuleDao
    abstract val nishthaModuleDetailDao : NishthaModuleDetailDao
    // For Debugging Purposes
    abstract val contactsDao: ContactsDao
}