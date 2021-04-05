package com.example.trial_webanddatabase

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.security.auth.callback.Callback
import kotlin.concurrent.thread

class HttpUtil {
    fun sendHttpRequest(address: String, listener: HttpCallbackListener){
        thread {
            var connetion:HttpURLConnection ?= null
            try{
                val response = StringBuilder()
                val url = URL(address)
                connetion = url.openConnection() as HttpURLConnection
                connetion.connectTimeout = 8000
                connetion.readTimeout = 8000
                val input = connetion.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                listener.onFinish(response.toString())
            }catch(e : Exception){
                e.printStackTrace()
                listener.onError(e)
            }finally {
                connetion ?. disconnect()
            }
        }
    }

    public fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
//        thread {
//            try {
//                val client =OkHttpClient()
//                val request = Request.Builder().url(address).build()
//                val response = client.newCall(request).execute()
//                val responseData = response.body?.string()
//                if(responseData != null){
//                    return response.toString()
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }
}