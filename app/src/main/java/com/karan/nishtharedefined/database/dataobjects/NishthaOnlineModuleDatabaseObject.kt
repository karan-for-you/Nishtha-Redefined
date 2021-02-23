package com.karan.nishtharedefined.database.dataobjects

import androidx.room.PrimaryKey

data class NishthaOnlineModuleDatabaseObject(
    @PrimaryKey val modId: String?,
    val modName: String?,
    val modLang: String?
)
