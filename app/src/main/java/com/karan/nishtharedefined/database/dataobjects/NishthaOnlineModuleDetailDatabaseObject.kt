package com.karan.nishtharedefined.database.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaOnlineModuleDetailDatabaseObject(
    val resource__name: String,
    @PrimaryKey val resource__link: String,
    val resource__type: String,
    val resource__html: String
)
