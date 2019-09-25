package com.example.android.effe2019

class Data {
    var title: String? = null
    var note: String? = null
    var date: String? = null
    private var id: String? = null

    constructor() {}
    constructor(id: String, title: String, note: String, date: String) {
        this.id = id
        this.title = title
        this.note = note
        this.date = date
    }

    fun getid(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }
}
