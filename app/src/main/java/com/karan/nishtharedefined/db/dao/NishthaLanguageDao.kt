package com.karan.nishtharedefined.db.dao

import androidx.room.*
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage

@Dao
interface NishthaLanguageDao {
    @Query("SELECT * FROM NishthaOnlineLanguage")
    fun getLanguages() : List<NishthaOnlineLanguage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLanguages(languages : ArrayList<NishthaOnlineLanguage>) : Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguage(language : NishthaOnlineLanguage) : Long

    @Query("DELETE FROM NishthaOnlineLanguage")
    fun deleteAllLanguages()
}