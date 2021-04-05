package com.example.trial_webanddatabase

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.Exception

class JsonParseUtil {
     public fun parseJsonWithGSON(jsonData:String):List<App>{
         val gson = Gson()
         val typeOf = object : TypeToken<List<App>>(){}.type
         val appList = gson.fromJson<List<App>>(jsonData, typeOf)
//         for(app in appList){
//             Log.d("WebActivity","id is ${app.id}")
//             Log.d("WebActivity","name is ${app.name}")
//             Log.d("WebActivity","version is ${app.version}")
//         }
         return appList
     }
}