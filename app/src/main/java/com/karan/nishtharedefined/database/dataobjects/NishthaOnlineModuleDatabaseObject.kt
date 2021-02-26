package com.karan.nishtharedefined.database.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaOnlineModuleDatabaseObject(
    @PrimaryKey val modId: String?,
    val modName: String?,
    val modLang: String?
)
