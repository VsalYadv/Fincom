package com.example.myapplication.model

import android.os.Parcelable
import io.realm.kotlin.types.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(

) :Parcelable, RealmObject{
    var name: String = ""
    var age: Int=0
    var city: String=""

    constructor(name:String,age:Int,city:String): this(){
        this.name=name
        this.age=age
        this.city=city
    }
}

data class AuthUser(
    val userName: String,
    val password: String
)