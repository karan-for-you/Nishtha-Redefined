package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage

@Dao
interface NishthaLanguageDao {
    @Query("SELECT * FROM NishthaOnlineLanguage")
    fun getContacts() : List<NishthaOnlineLanguage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLanguages(languages : ArrayList<NishthaOnlineLanguage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguage(language : NishthaOnlineLanguage): String

    @Query("DELETE FROM NishthaOnlineLanguage")
    fun deleteAllContacts()
}