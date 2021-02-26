package com.karan.nishtharedefined.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.karan.nishtharedefined.db.Contact

@Dao
interface ContactsDao {
    @Query("SELECT * FROM Contact")
    fun getContacts() : List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContacts(vararg contacts : Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact : Contact)

    @Query("DELETE FROM Contact")
    fun deleteAllContacts()
}