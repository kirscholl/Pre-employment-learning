package com.example.trial_webanddatabase

import java.lang.Exception

interface HttpCallbackListener {
    fun onFinish(response:String)
    fun onError(e:Exception)
}