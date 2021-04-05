package com.example.trial_webanddatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_database.*
import kotlinx.android.synthetic.main.activity_web.*

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val dbHelper = MyDatabaseHelper(this,"Games.db",2)

        btn_query.setOnClickListener{
            val db = dbHelper.writableDatabase

            val cursor = db.query("Games",null,null,
                null,null,null,null)
            if(cursor.moveToFirst()){
                do{
                    //遍历查询Games表中所有的数据并打印
                    val id = cursor.getString(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val version = cursor.getString(cursor.getColumnIndex("version"))

                    Log.d("DatabaseActivity","Game id is $id")
                    Log.d("DatabaseActivity","Game name is $name")
                    Log.d("DatabaseActivity","Game version is $version")
                }while (cursor.moveToNext())
            }
            cursor.close()

            Toast.makeText(this,"All the information of database is here"
                ,Toast.LENGTH_SHORT).show()
        }

        btn_clear.setOnClickListener{
            val db = dbHelper.writableDatabase
            db.delete("Games","id > ?", arrayOf("0"))
            Toast.makeText(this,"Database is cleared",Toast.LENGTH_SHORT).show()
        }

        btn_switch2.setOnClickListener{
            val intent = Intent(this,WebActivity::class.java)
            startActivity(intent)
        }
    }
}