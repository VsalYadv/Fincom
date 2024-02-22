package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.User
import com.example.myapplication.R
import com.example.myapplication.UserList
import java.util.Collections

class UserAdapter(private val arrayList: ArrayList<User>, private val listener:(user: User)-> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(item : View):RecyclerView.ViewHolder(item){
        val name : TextView = item.findViewById(R.id.userName)
        val age : TextView = item.findViewById(R.id.userAge)
        val city: TextView = item.findViewById(R.id.city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_items,parent,false)
        return  UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = arrayList[position]
        holder.name.text = userData.name
        holder.age.text = userData.age.toString()
        holder.city.text = userData.city
        holder.name.setOnClickListener {
            listener.invoke(userData)
        }
    }

    fun sortList(sortOrder:Int){
        if (sortOrder == UserList.sortAscending){
            arrayList.sortBy {
                it.name
            }
        } else {
            arrayList.reverse()
        }
        notifyDataSetChanged()
    }
    fun sortListByCity(sortOrder:Int){
        if (sortOrder == UserList.sortAscending){
            arrayList.sortBy {
                it.city
            }
        } else {
            arrayList.reverse()
        }
        notifyDataSetChanged()
    }

    fun sortListByAge(sortOrder:Int){
        if (sortOrder == UserList.sortAscending){
            arrayList.sortBy {
                it.age
            }
        } else {
            arrayList.reverse()
        }
        notifyDataSetChanged()
    }


}