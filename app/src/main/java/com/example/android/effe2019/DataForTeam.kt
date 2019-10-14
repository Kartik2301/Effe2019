package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForTeam : Parcelable {
    var name: String? = null
    var imageurl: String? = null
    var postition: String? = null
    var contact: Long? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.imageurl)
        dest.writeString(this.postition)
        dest.writeValue(this.contact)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.imageurl = `in`.readString()
        this.postition = `in`.readString()
        this.contact = `in`.readValue(Long::class.java.classLoader) as Long?
    }

    companion object {

        val CREATOR: Parcelable.Creator<DataForTeam> = object : Parcelable.Creator<DataForTeam> {
            override fun createFromParcel(source: Parcel): DataForTeam {
                return DataForTeam(source)
            }

            override fun newArray(size: Int): Array<DataForTeam?> {
                return arrayOfNulls(size)
            }
        }
    }
}
