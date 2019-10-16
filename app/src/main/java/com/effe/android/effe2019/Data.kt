package com.effe.android.effe2019

class Data {
    var name: String? = null
    var github: String? = null
    var designation: String? = null
    constructor() {}
    constructor(name: String, github:String, designation: String) {
        this.name = name
        this.github = github
        this.designation = designation
    }

    fun getNameofDev(): String? {
        return name
    }

    fun getGithubDev(): String? {
        return github
    }
    fun getDesignationDev(): String? {
        return designation
    }

}
