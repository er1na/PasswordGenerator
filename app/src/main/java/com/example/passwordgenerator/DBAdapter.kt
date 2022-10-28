package com.example.passwordgenerator

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import java.lang.Exception

class DBAdapter {

    private val DB_NAME: String = "passwordList.db"
    private val DB_TABLE: String = "passwordLists"
    private val DB_VERSION = 1

    private val LIST_ID: String = "_id"
    private val TITLE: String = "title"
    private val USER_ID: String = "userId"
    private val PASSWORD: String = "password"

    lateinit private var db : SQLiteDatabase
    lateinit private var dbHelper : DBHelper
    lateinit protected var context : Context

    fun DBAdapter(context : Context){
        this.context = context
        val dbHelper = DBHelper(this.context)
    }

    fun openDB() : DBAdapter{
        val db = dbHelper.getWritableDatabase()
        return this
    }

    fun closeDB(){
        db.close()
        var db : SQLiteDatabase? = null
    }

    fun saveDB(Title : String, UserId : String, Password : String){

        db.beginTransaction()

        try{

            val values = ContentValues()
            values.put(TITLE, Title)
            values.put(USER_ID, UserId)
            values.put(PASSWORD, Password)

            db.insert(DB_TABLE, null, values)
            db.setTransactionSuccessful()

        }catch(e: Exception){

            e.printStackTrace()

        }finally {

            db.endTransaction()

        }

    }

    fun getDB(columns : Array<String>) : Cursor {
        return db.query(DB_TABLE, columns, null, null, null, null, null)
    }

    fun searchDB(columns: Array<String>, column: String, name: Array<String>): Cursor{
        return db.query(DB_TABLE, columns, column + "like ?", name, null, null, null)
    }

    fun allDelete(){
        db.beginTransaction()

        try{
            db.delete(DB_TABLE, null, null)
            db.setTransactionSuccessful()
        }catch(e: Exception){
            e.printStackTrace()
        }finally {
            db.endTransaction()
        }
    }

    fun selectDelete(position: String){
        db.beginTransaction()

        try{
            db.delete(DB_TABLE, LIST_ID + "=?", arrayOf<String>(position))
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            db.endTransaction()
        }
    }

    inner class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase){
            val createTable = "CREATE TABLE " + DB_TABLE + " (" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TITLE + " TEXT NOT NULL," +
                    USER_ID + " TEXT NOT NULL," +
                    PASSWORD + " TEXT NOT NULL" + ");"

            db.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS" + DB_TABLE)
            onCreate(db)
        }
    }

}