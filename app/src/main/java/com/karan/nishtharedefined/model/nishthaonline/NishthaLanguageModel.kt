package com.karan.nishtharedefined.model.nishthaonline

import android.os.Parcel
import android.os.Parcelable

data class NishthaLanguageModel(
    val langText: String?,
    val langCode: String?,
    val langName: String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(langText)
        parcel.writeString(langCode)
        parcel.writeString(langName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NishthaLanguageModel> {
        override fun createFromParcel(parcel: Parcel): NishthaLanguageModel {
            return NishthaLanguageModel(parcel)
        }

        override fun newArray(size: Int): Array<NishthaLanguageModel?> {
            return arrayOfNulls(size)
        }
    }

}
