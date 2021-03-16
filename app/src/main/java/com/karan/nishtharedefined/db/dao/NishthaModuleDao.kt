package com.karan.nishtharedefined.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karan.nishtharedefined.db.dataobjects.NishthaModule

@Dao
interface NishthaModuleDao {
    @Query("SELECT * FROM NishthaModule")
    fun getModules() : List<NishthaModule>

    @Query("SELECT * FROM NishthaModule WHERE modLang = :modLang")
    fun getModules(modLang : String?) : List<NishthaModule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModules(modules : ArrayList<NishthaModule>) : Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModule(module : NishthaModule) : Long

    @Query("DELETE FROM NishthaModule")
    fun deleteAllModules()
}