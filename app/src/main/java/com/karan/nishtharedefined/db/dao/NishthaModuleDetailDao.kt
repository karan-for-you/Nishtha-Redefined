package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleDetail

@Dao
interface NishthaModuleDetailDao {
    @Query("SELECT * FROM NishthaModuleDetail")
    fun getModuleDetails() : List<NishthaModuleDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModuleDetails(moduleDetails : ArrayList<NishthaModuleDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModuleDetails(moduleDetails : NishthaModuleDetail)

    @Query("DELETE FROM NishthaModuleDetail")
    fun deleteAllModuleDetails()
}