package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NishthaModuleResource(
    val resource__name: String,
    @PrimaryKey val resource__link: String,
    val resource__type: String,
    val resource__html: String,
    val module : String,
    val lang : String
)