package com.karan.nishtharedefined.model.facetoface

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json


data class ModelResourceType(
    @Json(name = "id") val id: String?,
    @Json(name = "res_d_text_url") val res_d_text_url: String?,
    @Json(name = "res_v_text_url") val res_v_text_url: String?,
    @Json(name = "res_d_video_url") val res_d_video_url: String?,
    @Json(name = "res_v_video_url") val res_v_video_url: String?,
    @Json(name = "res_d_present_url") val res_d_present_url: String?,
    @Json(name = "res_v_present_url") val res_v_present_url: String?,
    @Json(name = "language") val language: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(res_d_text_url)
        parcel.writeString(res_v_text_url)
        parcel.writeString(res_d_video_url)
        parcel.writeString(res_v_video_url)
        parcel.writeString(res_d_present_url)
        parcel.writeString(res_v_present_url)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelResourceType> {
        override fun createFromParcel(parcel: Parcel): ModelResourceType {
            return ModelResourceType(parcel)
        }

        override fun newArray(size: Int): Array<ModelResourceType?> {
            return arrayOfNulls(size)
        }
    }

}
