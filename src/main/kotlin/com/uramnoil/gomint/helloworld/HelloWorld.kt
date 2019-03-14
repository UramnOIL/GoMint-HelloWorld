package com.uramnoil.gomint.helloworld

import okhttp3.OkHttpClient
import okhttp3.Request

class HelloWorld {
    fun getHelloWorld(): String {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://gist.githubusercontent.com/UramnOIL/7f2d78ebd4bb0482336e1d759cdd2229/raw/df8ecea0dff9eb463a716a8117d996bf28ac29e7/helloworld.txt").build()
        val response = client.newCall(request).execute()
        return response.body()?.string() ?: ""
    }
}