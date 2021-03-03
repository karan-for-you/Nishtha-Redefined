package com.karan.nishtharedefined.db.dataobjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey val id: Int,
    val contactName: String,
    val contactNumber : String
)
