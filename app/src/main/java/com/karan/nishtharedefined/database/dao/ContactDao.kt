package com.karan.nishtharedefined.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.database.dataobjects.Contact

interface ContactDao {

    @Query("SELECT * FROM Contact")
    fun getLanguages() : LiveData<ArrayList<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContacts(vararg contacts : Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact : Contact)
}