package com.karan.nishtharedefined.model

import com.squareup.moshi.Json

data class ModelResourceType(
    @Json(name = "id") val id: String,
    @Json(name = "res_d_text_url") val res_d_text_url: String,
    @Json(name = "res_v_text_url") val res_v_text_url: String,
    @Json(name = "res_d_video_url") val res_d_video_url: String,
    @Json(name = "res_v_video_url") val res_v_video_url: String,
    @Json(name = "res_d_present_url") val res_d_present_url: String,
    @Json(name = "res_v_present_url") val res_v_present_url: String,
    @Json(name = "language") val language: String
)
