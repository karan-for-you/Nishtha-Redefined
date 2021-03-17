package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaModule
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleResource

@Dao
interface NishthaModuleResourceDao {
    @Query("SELECT * FROM NishthaModuleResource")
    fun getModuleDetails() : List<NishthaModuleResource>

    @Query("SELECT * FROM NishthaModuleResource WHERE lang = :modLang AND module = :module" )
    fun getModuleDetails(modLang : String?, module : String?) : List<NishthaModuleResource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModuleDetails(moduleResources : ArrayList<NishthaModuleResource>) : Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModuleDetails(moduleDetails : NishthaModuleResource) : Long

    @Query("DELETE FROM NishthaModuleResource")
    fun deleteAllModuleDetails()
}