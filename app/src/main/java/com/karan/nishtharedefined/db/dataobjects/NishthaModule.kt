package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaModule(
    @PrimaryKey val modId: String,
    val modName: String,
    val modLang: String
)
