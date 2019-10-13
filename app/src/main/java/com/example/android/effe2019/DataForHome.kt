package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable


class DataForHome : Parcelable {
    var description: String? = null

    var eventID: Int = 0

    var senderEmail: String? = null

    var senderName: String? = null

    var timestamp: Long = 0

    var title: String? = null

    var verified: Boolean = true

    constructor() {}

    constructor(
        title: String,
        description: String,
        timestamp: Long,
        eventID: Int,
        senderEmail: String,
        senderName: String,
        verified: Boolean
    ) {
        this.title = title
        this.description = description
        this.timestamp = timestamp
        this.eventID = eventID
        this.senderEmail = senderEmail
        this.senderName = senderName
        this.verified = verified
    }
    
    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeInt(eventID)
        parcel.writeString(senderEmail)
        parcel.writeString(senderName)
        parcel.writeLong(timestamp)
        parcel.writeString(title)
        parcel.writeByte(if (verified) 1 else 0)
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