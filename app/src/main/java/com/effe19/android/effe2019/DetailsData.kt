package com.effe19.android.effe2019


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

class DetailsData : Parcelable {
    val title: String?
    val text: String?

    constructor(title: String, text: String) {
        this.title = title
        this.text = text
    }

    private constructor(`in`: Parcel) {
        title = `in`.readString()
        text = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(title)
        parcel.writeString(text)
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<DetailsData> = object : Parcelable.Creator<DetailsData> {
            override fun createFromParcel(parcel: Parcel): DetailsData {
                return DetailsData(parcel)
            }

            override fun newArray(size: Int): Array<DetailsData?> {
                return arrayOfNulls(size)
            }
        }
    }

}
