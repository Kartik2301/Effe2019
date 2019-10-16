package com.effe.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForOrganizers : Parcelable {
    var name: String? = null
    var phoneNumber: Long? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeValue(this.phoneNumber)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.phoneNumber = `in`.readValue(Long::class.java.classLoader) as Long?
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataForOrganizers> =
            object : Parcelable.Creator<DataForOrganizers> {
                override fun createFromParcel(source: Parcel): DataForOrganizers {
                    return DataForOrganizers(source)
                }

                override fun newArray(size: Int): Array<DataForOrganizers?> {
                    return arrayOfNulls(size)
                }
            }
    }
}
