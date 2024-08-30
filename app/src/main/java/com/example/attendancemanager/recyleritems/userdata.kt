package com.example.attendancemanager.recyleritems

data class userdata (
    var subject : String?,
    var present : String?,
    var totalclasses: String?,
    var progressbar : Float
){
    // Add a no-argument constructor
    constructor() : this("", "","",0.0f)
}