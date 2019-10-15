package com.example.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForTeam : Parcelable {
    var name: String? = null
    var imageUrl: String? = null
    var position: String? = null
    var contact: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.imageUrl)
        dest.writeString(this.position)
        dest.writeValue(this.contact)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.imageUrl = `in`.readString()
        this.position = `in`.readString()
        this.contact = `in`.readValue(String::class.java.classLoader) as String?
    }

    companion object {
        @JvmField
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
