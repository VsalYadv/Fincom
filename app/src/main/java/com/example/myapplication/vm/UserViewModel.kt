package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AuthUser
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class UserViewModel : ViewModel() {

    private val TAG = "UserViewModel"

    var databaseReference: DatabaseReference

    private val _isSuccessFullLogin = MutableLiveData<Boolean>()
    val loggedInSuccesfull: LiveData<Boolean>
        get() = _isSuccessFullLogin

    val eventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val dataUserName = snapshot.child("userName").value
            val dataPassword = snapshot.child("password").value
            _isSuccessFullLogin.postValue(userNameCheck == dataUserName && dataPassword == passwordCheck)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    init {
        val firebaseDatabase: FirebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.getReference("auth_user")

        storeData()
    }

    fun storeData(){
        Log.d(TAG, "storeData: ")
        databaseReference.setValue(AuthUser("Fininfocom","Fin@123"))
    }

    lateinit var userNameCheck:String
    lateinit var passwordCheck:String

    fun checkUserLogin(userName:String,password:String) {
        userNameCheck = userName
        passwordCheck = password


        databaseReference.addValueEventListener(eventListener)
    }

    fun removeListener() {
        databaseReference.addValueEventListener(eventListener)
    }

     fun isValidUsername(username: String): Boolean {
        return username.length == 10
    }

    fun isValidPassword(password: String): Boolean {
        val pattern = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{7,}$")
        return pattern.matches(password)
    }

}