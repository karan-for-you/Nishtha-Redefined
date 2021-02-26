package com.karan.nishtharedefined.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineModuleDatabaseObject

interface NishthaOnlineModuleDao {

    @Query("SELECT * FROM NishthaOnlineModuleDatabaseObject")
    fun getModules() : LiveData<ArrayList<NishthaOnlineModuleDatabaseObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModules(vararg nishthaOnlineModuleLanguages : NishthaOnlineModuleDatabaseObject)
}