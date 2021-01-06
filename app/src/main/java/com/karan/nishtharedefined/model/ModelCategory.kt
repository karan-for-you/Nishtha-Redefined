package com.karan.nishtharedefined.model

import com.squareup.moshi.Json

data class ModelCategory(
    @Json(name = "id" )val id: String,
    @Json(name = "cat_name" )val cat_name: String,
    @Json(name = "lang" )var lang: String
)