package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.model.User

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val user : User? = intent.extras?.getParcelable(UserList.USER_DATA)
        user?.let {
            val userName = findViewById<TextView>(R.id.userName)
            userName.text= it.name
            val city = findViewById<TextView>(R.id.city)
            city.text= it.city
            val age = findViewById<TextView>(R.id.userAge)
            age.text= it.age.toString()

        }
    }
}