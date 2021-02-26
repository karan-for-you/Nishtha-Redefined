package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.Contact

@Dao
interface ContactsDao {
    @Query("SELECT * FROM Contact")
    fun getContacts() : List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContacts(vararg contacts : Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact : Contact): Long

    @Query("DELETE FROM Contact")
    fun deleteAllContacts()
}