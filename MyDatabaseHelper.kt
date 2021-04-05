package com.example.trial_webanddatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context, name: String, version: Int)
    :SQLiteOpenHelper(context, name, null, version) {

    private val createGameStore = "Create table Games ("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "version integer)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createGameStore)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion <= 1){
            //...
        }
    }
}