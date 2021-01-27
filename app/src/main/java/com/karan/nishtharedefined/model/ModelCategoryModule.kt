package com.karan.nishtharedefined.model

import com.squareup.moshi.Json

data class ModelCategoryModule(
    @Json(name = "id") val id : String,
    @Json(name = "mod_name") val mod_name : String,
    @Json(name = "language") val language : String
)
