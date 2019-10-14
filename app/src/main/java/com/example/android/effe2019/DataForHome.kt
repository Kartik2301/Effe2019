package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForHome : Parcelable {
    var description: String? = null
    var eventID: Int = 0
    var senderEmail: String? = null
    var senderName: String? = null
    var timestamp: Long? = null
    var title: String? = null
    var verified: Boolean? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.description)
        dest.writeInt(this.eventID)
        dest.writeString(this.senderEmail)
        dest.writeString(this.senderName)
        dest.writeValue(this.timestamp)
        dest.writeString(this.title)
        dest.writeValue(this.verified)
    }

    protected constructor(`in`: Parcel) {
        this.description = `in`.readString()
        this.eventID = `in`.readInt()
        this.senderEmail = `in`.readString()
        this.senderName = `in`.readString()
        this.timestamp = `in`.readValue(Long::class.java.classLoader) as Long?
        this.title = `in`.readString()
        this.verified = `in`.readValue(Boolean::class.java.classLoader) as Boolean?
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataForHome> = object : Parcelable.Creator<DataForHome> {
            override fun createFromParcel(source: Parcel): DataForHome {
                return DataForHome(source)
            }

            override fun newArray(size: Int): Array<DataForHome?> {
                return arrayOfNulls(size)
            }
        }
    }
}
