package com.karan.nishtharedefined.model

import com.squareup.moshi.Json

data class ModelLanguage
    (
    @Json(name = "id")
    val id: String,
    @Json(name = "avail_lang_list")
    val avail_lang_list: String)
