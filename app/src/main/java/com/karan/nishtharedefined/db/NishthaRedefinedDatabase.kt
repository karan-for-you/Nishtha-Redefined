package com.karan.nishtharedefined.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.db.dao.ContactsDao
import com.karan.nishtharedefined.db.dao.NishthaLanguageDao
import com.karan.nishtharedefined.db.dao.NishthaModuleDao
import com.karan.nishtharedefined.db.dao.NishthaModuleResourceDao
import com.karan.nishtharedefined.db.dataobjects.Contact
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleResource
import com.karan.nishtharedefined.db.dataobjects.NishthaModule
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage

@Database(
    entities = [
        Contact::class,
        NishthaOnlineLanguage::class,
        NishthaModule::class,
        NishthaModuleResource::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val nishthaLanguageDao: NishthaLanguageDao
    abstract val nishthaModuleDao: NishthaModuleDao
    abstract val nishthaModuleResourceDao : NishthaModuleResourceDao
    // For Debugging Purposes
    abstract val contactsDao: ContactsDao
}