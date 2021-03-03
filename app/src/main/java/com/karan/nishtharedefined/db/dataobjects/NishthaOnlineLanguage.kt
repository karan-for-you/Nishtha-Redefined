package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaOnlineLanguage(
    @PrimaryKey val langCode: String,
    val langText: String,
    val langName: String
)
