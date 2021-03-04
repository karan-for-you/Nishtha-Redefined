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
    fun getModules() : List<NishthaModuleModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllModules(modules : ArrayList<NishthaModuleModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModule(module : NishthaModuleModel)

    @Query("DELETE FROM NishthaModuleModel")
    fun deleteAllModules()
}