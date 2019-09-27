package com.example.android.effe2019

import java.sql.Timestamp

class DataforHome{
    var title: String? = null
    var description: String? = null
    var timestamp: Long? = null
    constructor() {}
    constructor(title: String, description:String, timestamp: Long) {
        this.title = title
        this.description = description
        this.timestamp = timestamp
    }
    fun getTitleofEvent(): String? {
        return title
    }
    fun getDesciprionofEvent(): String? {
        return description
    }
    fun getTimeStampofEvent(): Long?{
        return timestamp
    }
}
