package com.example.trial_webanddatabase

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_web.*
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import javax.security.auth.callback.Callback

class WebActivity : AppCompatActivity() {
    var jsonParse = JsonParseUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val dbHelper = MyDatabaseHelper(this,"GameStore.db",2)

        btn_show_web1.setOnClickListener{
//            val intent = Intent(Intent.ACTION_VIEW)
//            //使用Apache监听了81端口,搭建了一个小型的web服务器
//            intent.setData(Uri.parse("http://10.0.2.2:81/web1.json"))
//            startActivity(intent)

            web_view.settings.javaScriptEnabled
            web_view.webViewClient = WebViewClient()
            web_view.loadUrl("http://10.0.2.2:81/web1.json")
        }

        btn_save_web1.setOnClickListener{
            val address = "http://10.0.2.2:81/web1.json"
            val httpUtil =  HttpUtil()
            httpUtil.sendOkHttpRequest(address, object: okhttp3.Callback{
                override fun onResponse(call: Call, response: Response){
                    val responseData:String = response.body().string()

                    if(response != null){
                        var appList = jsonParse.parseJsonWithGSON(responseData)

                        //将解析出的对象依次存入数据库
                        dbHelper.writableDatabase
                        val db = dbHelper.writableDatabase

                        for (app in appList) {
                        var value = ContentValues().apply {
                            put("id",app.id)
                            put("name",app.name)
                            put("version",app.version)
                        }

                        db.insert("Games",null,value)

//                            Log.d("WebActivity","id is ${app.id}")
//                            Log.d("WebActivity","name is ${app.name}")
//                            Log.d("WebActivity","version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
            })

            Toast.makeText(this,"Saved data of web1_data to database"
                    ,Toast.LENGTH_SHORT).show()
        }

        btn_show_web2.setOnClickListener{
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.setData(Uri.parse("http://10.0.2.2:81/web2.json"))
//            startActivity(intent)

            web_view.settings.javaScriptEnabled
            web_view.webViewClient = WebViewClient()
            web_view.loadUrl("http://10.0.2.2:81/web2.json")
        }

        btn_save_web2.setOnClickListener{
            val address = "http://10.0.2.2:81/web2.json"
            val httpUtil =  HttpUtil()
            httpUtil.sendOkHttpRequest(address, object: okhttp3.Callback{
                override fun onResponse(call: Call, response: Response){
                    val responseData:String = response.body().string()

                    if(response != null){
                        var appList = jsonParse.parseJsonWithGSON(responseData)

                        //将解析出的对象依次存入数据库
                        dbHelper.writableDatabase
                        val db = dbHelper.writableDatabase

                        for (app in appList) {
                            var value = ContentValues().apply {
                                put("id",app.id)
                                put("name",app.name)
                                put("version",app.version)
                            }

                            db.insert("Games",null,value)

                            Log.d("WebActivity","id is ${app.id}")
                            Log.d("WebActivity","name is ${app.name}")
                            Log.d("WebActivity","version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
            })

            Toast.makeText(this,"Saved data of web1_data to database"
                    ,Toast.LENGTH_SHORT).show()
        }

        btn_switch1.setOnClickListener{
            val intent = Intent(this,DatabaseActivity::class.java)
            startActivity(intent)
        }
    }
}

//附表中数据
//web1.json
//[{"id":"1","version":"5.5","name":"Clash of Clans"},
//{"id":"2","version":"7.0","name":"Boom Beach"},
//{"id":"3","version":"3.5","name":"Clash Royale"}]
//web2.json
//[{"id":"4","version":"8.0","name":"Maple Story"},
//{"id":"5","version":"10.4","name":"Brawl Stars"},
//{"id":"6","version":"2.0.0","name":"League of Legends"}]