package com.example.myapplication.database

import com.example.myapplication.model.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.notifications.InitialRealm
import io.realm.kotlin.notifications.UpdatedRealm

object RealmConfig {
    // Creating our db with custom properties
    val config = RealmConfiguration.Builder(schema = setOf(User::class))
        .name("test.db")
        .schemaVersion(1)
        .build()
    val realm =Realm.open(config)


    fun storeVAlue(){
        val array = arrayListOf<User>().apply {
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
            add(User("Bishal", 25, "Bangalore"))
        }

            array.forEach{
                realm.writeBlocking {
                    copyToRealm(it)
                }
            }

    }

    fun read(){

    }


}