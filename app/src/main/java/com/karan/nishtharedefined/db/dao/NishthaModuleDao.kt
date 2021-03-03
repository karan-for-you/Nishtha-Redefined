package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleModel
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage

@Dao
interface NishthaModuleDao {
    @Query("SELECT * FROM NishthaModuleModel")
    fun getContacts() : List<NishthaOnlineLanguage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModules(languages : ArrayList<NishthaModuleModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModule(language : NishthaModuleModel): String

    @Query("DELETE FROM NishthaModuleModel")
    fun deleteAllModules()
}