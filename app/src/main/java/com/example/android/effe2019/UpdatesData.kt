package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable

data class UpdatesData (
    var description: String?,
    var eventID: Int,
    var senderEmail: String?,
    var senderName: String?,
    var timestamp: Long,
    var title: String?,
    var verified: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

//    constructor() : this() {}
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeInt(eventID)
        parcel.writeString(senderEmail)
        parcel.writeString(senderName)
        parcel.writeLong(timestamp)
        parcel.writeString(title)
        parcel.writeByte(if (verified) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpdatesData> {
        override fun createFromParcel(parcel: Parcel): UpdatesData {
            return UpdatesData(parcel)
        }

        override fun newArray(size: Int): Array<UpdatesData?> {
            return arrayOfNulls(size)
        }
    }
}