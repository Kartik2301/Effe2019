package com.effe.android.effe2019

import android.os.Parcel
import android.os.Parcelable

class DataForEvents(
    var categories: String? = null,
    var date: String? = null,
    var description: String? = null,
    var facebookEventLink: String? = null,
    var id: Int = 0,
    var imageUrl: String? = null,
    var location: String? = null,
    var name: String? = null,
    var organizers: List<DataForOrganizers>? = null,
    var time: String? = null,
    var timestamp: Long = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.createTypedArrayList(DataForOrganizers.CREATOR),
        source.readString(),
        source.readValue(Long::class.java.classLoader) as Long
    )

    constructor() : this(
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        emptyList(),
        "",
        0)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(categories)
        writeString(date)
        writeString(description)
        writeString(facebookEventLink)
        writeInt(id)
        writeString(imageUrl)
        writeString(location)
        writeString(name)
        writeTypedList(organizers)
        writeString(time)
        writeValue(timestamp)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataForEvents> =
            object : Parcelable.Creator<DataForEvents> {
                override fun createFromParcel(source: Parcel): DataForEvents = DataForEvents(source)
                override fun newArray(size: Int): Array<DataForEvents?> = arrayOfNulls(size)
            }
    }
}