package com.example.sql_lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sql_lesson.model.contact.adapter.DataUser

class AdapterClass(var userList:MutableList<DataUser>, var contInterface: ContactInterface):RecyclerView.Adapter<AdapterClass.HolderClass>() {

    class HolderClass(itemview: View) :RecyclerView.ViewHolder(itemview){
        val name_holder:TextView=itemview.findViewById(R.id.name)
        val number_holder:TextView=itemview.findViewById(R.id.tel)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linear)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.rv_contact,parent,false)
        return HolderClass(itemview)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        val currentItem=userList[position]
        holder.name_holder.text=currentItem.name_dataClass
        holder.number_holder.text=currentItem.contact_dataClass
        holder.linearLayout.setOnClickListener {
            contInterface.onClick(currentItem)
        }
    }
    interface ContactInterface {
        fun onClick(contact: DataUser) {}
    }
}