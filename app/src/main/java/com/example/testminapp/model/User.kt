package com.example.testminapp.model

import java.text.DateFormat

data class User(
    val name:String,
    val email:String,
    val photoUri:String,
    val createdDate:Long =System.currentTimeMillis()
){
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(createdDate)
}
