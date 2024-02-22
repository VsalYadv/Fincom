package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.RealmConfig
import com.example.myapplication.vm.UserViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.realm.kotlin.Realm

class MainActivity : AppCompatActivity() {

    val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        viewModel.storeData()

        viewModel.loggedInSuccesfull.observe(this){
            if(it){
                val intent = Intent(this,UserList::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }else{
                Toast.makeText(
                    this@MainActivity,
                    "Invalid username or password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val userId = findViewById<EditText>(R.id.userId)
        val userPassWord = findViewById<EditText>(R.id.userPassword)
        findViewById<AppCompatButton>(R.id.btnLogin).apply {
            setOnClickListener {
                if(!viewModel.isValidUsername(userId.text.toString())){
                    Toast.makeText(
                        this@MainActivity,
                        "Username must be10 characters",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                if(!viewModel.isValidPassword(userPassWord.text.toString())){
                    Toast.makeText(
                        this@MainActivity,
                        "Password must be 7 characters with one uppercase alphabet, one special character, and one numeric",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }

                viewModel.checkUserLogin(userId.text.toString(), password = userPassWord.text.toString())
            }
        }


    }


    private fun navigate() {
        val intent = Intent(this, UserList::class.java)
        startActivity(intent)
    }



}