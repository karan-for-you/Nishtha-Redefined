package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaModule(
    val modId: String,
    @PrimaryKey val modName: String,
    val modLang: String
)
