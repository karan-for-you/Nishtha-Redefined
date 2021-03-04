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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModules(modules : ArrayList<NishthaModule>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModule(module : NishthaModule)

    @Query("DELETE FROM NishthaModule")
    fun deleteAllModules()
}