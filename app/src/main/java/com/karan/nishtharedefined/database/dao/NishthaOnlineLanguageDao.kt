package com.karan.nishtharedefined.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineLanguageDatabaseObject

interface NishthaOnlineLanguageDao {

    @Query("SELECT * FROM NishthaOnlineLanguageDatabaseObject")
    fun getLanguages() : LiveData<ArrayList<NishthaOnlineLanguageDatabaseObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModuleLanguages(vararg nishthaOnlineModuleLanguages : NishthaOnlineLanguageDatabaseObject)

}