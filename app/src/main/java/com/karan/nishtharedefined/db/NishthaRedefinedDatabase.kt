package com.karan.nishtharedefined.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karan.nishtharedefined.db.dao.ContactsDao

@Database(
    entities = [
        Contact::class],
    version = 1,
    exportSchema = false
)
abstract class NishthaRedefinedDatabase : RoomDatabase() {
    abstract val contactsDao: ContactsDao
}