package com.example.myapplication.model

import io.realm.kotlin.types.RealmObject

class UserRealm( ) :RealmObject {

    var userName:String=""
    var age:Int=0
    var city:String=""
}