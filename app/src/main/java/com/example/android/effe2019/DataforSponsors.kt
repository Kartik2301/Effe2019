package com.example.android.effe2019

class DataforSponsors{
    var name: String? = null
    var imageUrl: String? = null
    lateinit var categories: List<String>
        constructor() {}
    constructor(name: String, imageUrl: String, categories: List<String>) {
        this.name = name
        this.imageUrl = imageUrl
        this.categories = categories
    }
    fun getNameofSponsor(): String? {
        return name
    }
    fun getUrlofSponsor(): String?{
        return imageUrl
    }
    fun getCategoriesoftheSponsor():List<String>?{
        return categories
    }
}

