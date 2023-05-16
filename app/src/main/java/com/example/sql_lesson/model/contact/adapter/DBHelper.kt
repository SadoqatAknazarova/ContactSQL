package com.example.sql_lesson

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sql_lesson.model.contact.adapter.DataUser


class DBHelper(context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object {
        const val DB_NAME = "userData"
        const val DB_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table 'contact'('id' integer PRIMARY KEY AUTOINCREMENT, 'name' TEXT NOT NULL, 'phone_number' STRING NOT NULL)"
        p0?.execSQL(query)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists UserData")
    }
    fun addContact(contact: DataUser){
        val writableDb = this.writableDatabase
        val values = ContentValues()
           values.put("name", contact.name_dataClass)
            values.put("phone_number", contact.contact_dataClass)
        writableDb.insert("contact",null,values)

    }
    fun saveUserData(name:String, contact:String): Boolean {
        val p=this.writableDatabase
        val cv= ContentValues()
        cv.put("name",name)
        cv.put("contact",contact)
        val result=p.insert("UserData",null,cv)
        if(result==-1.toLong()){
            return false
        }
        return true
    }

    fun getString(): Cursor? {
        val p = this.writableDatabase
        val cursor = p.rawQuery("select * from Userdata", null)
        return cursor
    }
    fun getContacts(): MutableList<DataUser> {
        var contacts = mutableListOf<DataUser>()
        var query="select * from contact"
        val db = this.readableDatabase
        var cursor:Cursor?=null
        cursor=readableDatabase.rawQuery(query,null)

        var id:Int
        var name:String
        var phone:String
        if (cursor.moveToFirst()){
            do {
                 id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
               name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
               phone =cursor.getString(cursor.getColumnIndexOrThrow("phone_number"))
                val emp= DataUser(id=id, name_dataClass = name, contact_dataClass =phone)
                contacts.add(emp)
                Log.d("TAG", "getContacts:"+"dfgkj")
            }
                while (cursor.moveToNext())
        }


        return contacts
    }

@SuppressLint("Range")
fun getContactsFilteredByName(searchText: String): MutableList<DataUser> {
            val contacts = mutableListOf<DataUser>()
            val selectQuery = "SELECT * FROM contact WHERE name LIKE '%$searchText%'"
            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val phone = cursor.getString(cursor.getColumnIndex("phone_number"))
                    val contact = DataUser(id, name, phone)
                    contacts.add(contact)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return contacts
        }

        fun deleteAllContacts() {
            val writableDb = this.writableDatabase
            writableDb.delete("contact", null, null)
        }
    fun editContact(contact: DataUser) {
        val writableDb = this.writableDatabase
        val cv= ContentValues()
        cv.put("name",contact.name_dataClass)
        cv.put("phone_number",contact.contact_dataClass)
        writableDb.update("contact",cv,"id = " +contact.id,null)
    }
        fun deleteContact(contact: DataUser) {
            val writableDb = this.writableDatabase
            writableDb.delete("contact", "id = ?", arrayOf(contact.id.toString()))
        }
    fun sortAZ(p0: SQLiteDatabase?) {
        val query = "create table 'contact'('id' integer PRIMARY KEY AUTOINCREMENT, 'name' TEXT NOT NULL , 'phone_number' STRING NOT NULL )  order by name"
        p0?.execSQL(query)
    }
}