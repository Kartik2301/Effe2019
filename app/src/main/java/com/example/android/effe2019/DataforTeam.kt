package com.example.android.effe2019


class DataforTeam{
    var name: String? = null
    var imageUrl: String? = null
    var position : String? = null
    var contact : Long? = null
    constructor() {}
    constructor(name: String, imageUrl: String, position: String, contact : Long) {
        this.name = name
        this.imageUrl = imageUrl
        this.position = position
        this.contact = contact
    }
    fun getNameofTeamMember(): String? {
        return name
    }
    fun getUrlofTeamMember(): String?{
        return imageUrl
    }
    fun getPositionofTeamMember(): String? {
        return position
    }
    fun getContactofTeamMember(): Long? {
        return contact
    }
}
