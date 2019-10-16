package com.effe.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForSponsors : Parcelable {
    var name: String? = null
    var imageUrl: String? = null
    var categories: List<String>? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.imageUrl)
        dest.writeStringList(this.categories)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.imageUrl = `in`.readString()
        this.categories = `in`.createStringArrayList()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataForSponsors> = object : Parcelable.Creator<DataForSponsors> {
            override fun createFromParcel(source: Parcel): DataForSponsors {
                return DataForSponsors(source)
            }

            override fun newArray(size: Int): Array<DataForSponsors?> {
                return arrayOfNulls(size)
            }
        }
    }
}
