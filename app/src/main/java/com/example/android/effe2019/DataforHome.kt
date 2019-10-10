package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class DataforHome : Parcelable {
    var description: String? = null

    var eventID: String? = null

    var senderEmail: String? = null

    var senderName: String? = null

    var timestamp: Long? = null

    var title: String? = null

    var verifed: Boolean = true

    constructor() {}

    constructor(title: String, description: String, timestamp: Long, eventID: String, senderEmail: String, senderName: String, verified: Boolean) {
        this.title = title
        this.description = description
        this.timestamp = timestamp
        this.eventID = eventID
        this.senderEmail = senderEmail
        this.senderName = senderName
        this.verifed = verifed
    }

    fun getTitleofEvent(): String? {
        return title
    }

    fun getDesciprionofEvent(): String? {
        return description
    }

    fun getTimeStampofEvent(): Long? {
        return timestamp
    }

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataforHome> = object : Parcelable.Creator<DataforHome> {
            override fun createFromParcel(source: Parcel): DataforHome = DataforHome(source)
            override fun newArray(size: Int): Array<DataforHome?> = arrayOfNulls(size)
        }
    }
}