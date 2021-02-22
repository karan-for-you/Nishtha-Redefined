package com.karan.nishtharedefined.database.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaOnlineLanguageDatabaseObject constructor(
    @PrimaryKey val languageCode : String,
                val languageText : String,
                val languageName : String
)
