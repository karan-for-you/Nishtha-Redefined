package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaModuleDetail(
    val resource__name: String,
    val resource__link: String,
    @PrimaryKey val resource__type: String,
    val resource__html: String
)
