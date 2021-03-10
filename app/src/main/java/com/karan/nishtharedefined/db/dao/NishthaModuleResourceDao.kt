package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleResource

@Dao
interface NishthaModuleResourceDao {
    @Query("SELECT * FROM NishthaModuleResource")
    fun getModuleDetails() : List<NishthaModuleResource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModuleDetails(moduleResources : ArrayList<NishthaModuleResource>) : Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModuleDetails(moduleDetails : NishthaModuleResource) : Long

    @Query("DELETE FROM NishthaModuleResource")
    fun deleteAllModuleDetails()
}