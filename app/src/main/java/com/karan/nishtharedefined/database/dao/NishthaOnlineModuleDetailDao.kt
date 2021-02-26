package com.karan.nishtharedefined.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.database.dataobjects.NishthaOnlineModuleDetailDatabaseObject

interface NishthaOnlineModuleDetailDao {
    @Query("SELECT * FROM NishthaOnlineModuleDetailDatabaseObject")
    fun getModuleDetails() : LiveData<ArrayList<NishthaOnlineModuleDetailDatabaseObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModuleDetails(vararg nishthaOnlineModuleLanguages : NishthaOnlineModuleDetailDatabaseObject)
}