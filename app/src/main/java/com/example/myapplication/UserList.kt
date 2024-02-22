package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.model.User
import io.realm.kotlin.Realm

class UserList : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    companion object{
         const val sortAscending = 1
         const val sortDescending = 2
         var mSort : Int = sortAscending
        const val USER_DATA = "USER_DATA"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val arrayList = arrayListOf<User>().apply {
            add(User("Vishal", 25, "Bangalore"))
            add(User("Praveen", 26, "kolkata"))
            add(User("Sagar", 27, "Chennai"))
            add(User("Sharad", 28, "New Delhi"))
            add(User("Rahul", 29, "Mumbai"))
            add(User("Rohit", 30, "Varanasi"))
            add(User("Pankaj", 31, "Allahabad"))
            add(User("Prem", 32, "Gorakhpur"))
            add(User("Avinash", 33, "Mau"))
            add(User("Pradeep", 34, "Darjeeling"))


        }
        recyclerView = findViewById(R.id.recyclerList)
        userAdapter = UserAdapter(arrayList, onUserClick)
        recyclerView.adapter = userAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.user_menu, menu)
        return  true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.filterList-> sortData()
             R.id.action_item2 -> sortDataByCity()
             R.id.action_item3-> sortByAge()
         }
      return super.onOptionsItemSelected(item)
    }

private fun sortData(){
    mSort =  if (mSort==sortAscending) sortDescending else sortAscending
    userAdapter.sortList(mSort)
    userAdapter.notifyDataSetChanged()
}

    private fun sortDataByCity(){
        mSort =  if (mSort==sortAscending) sortDescending else sortAscending
        userAdapter.sortListByCity(mSort)
        userAdapter.notifyDataSetChanged()
    }


    private fun sortByAge(){
        mSort =  if (mSort==sortAscending) sortDescending else sortAscending
        userAdapter.sortListByAge(mSort)
        userAdapter.notifyDataSetChanged()
    }




    private val onUserClick: (user: User) -> Unit = {user->
      val intent = Intent(this@UserList, UserDetails::class.java)
       intent.putExtra(USER_DATA, user)
        startActivity(intent)
    }



}