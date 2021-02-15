package com.karan.nishtharedefined.model

import android.os.Parcel
import android.os.Parcelable

data class NishthaModuleModel(
    val modId: String?,
    val modName: String?,
    val modLang: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(modId)
        parcel.writeString(modName)
        parcel.writeString(modLang)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NishthaModuleModel> {
        override fun createFromParcel(parcel: Parcel): NishthaModuleModel {
            return NishthaModuleModel(parcel)
        }

        override fun newArray(size: Int): Array<NishthaModuleModel?> {
            return arrayOfNulls(size)
        }
    }

}
