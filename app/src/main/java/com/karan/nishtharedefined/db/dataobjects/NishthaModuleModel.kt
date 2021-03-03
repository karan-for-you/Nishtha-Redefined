package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaModuleModel(
    @PrimaryKey val modId: String,
    val modName: String,
    val modLang: String
)
