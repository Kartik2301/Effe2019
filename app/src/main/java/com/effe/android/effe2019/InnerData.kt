package com.effe.android.effe2019

class InnerData(
    val Orgaizser: String,
    val title: String,
    val Location: String,
    val Time: String,
    val description: String,
    val Url: String,
    val ParentUrl: String,
    val date: String,
    var organizers: List<DataForOrganizers>? = null

)

