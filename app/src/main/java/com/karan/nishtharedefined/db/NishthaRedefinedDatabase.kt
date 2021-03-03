package com.karan.nishtharedefined.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.db.dao.ContactsDao
import com.karan.nishtharedefined.db.dao.NishthaLanguageDao
import com.karan.nishtharedefined.db.dataobjects.Contact

@Database(
    entities = [
        Contact::class,
        NishthaLanguageDao::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val contactsDao: ContactsDao
    abstract val nishthaLanguageDao : NishthaLanguageDao
}