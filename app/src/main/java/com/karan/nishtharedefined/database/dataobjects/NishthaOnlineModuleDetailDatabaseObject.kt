package com.karan.nishtharedefined.database.dataobjects

import androidx.room.PrimaryKey

data class NishthaOnlineModuleDetailDatabaseObject(
    val resource__name: String,
    @PrimaryKey val resource__link: String,
    val resource__type: String,
    val resource__html: String
)
