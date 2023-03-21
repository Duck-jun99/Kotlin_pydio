package com.kotlinstudy.kotlin_pydio

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class MyServerPydio {
    fun getFiles(): List<String> {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("myserver")
            .get()
            .addHeader("admin", "Bearer My_access_token")
            .build()
        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                val json = JSONObject(responseBody)
                val files = json.getJSONArray("data")
                val fileNames = mutableListOf<String>()
                for (i in 0 until files.length()) {
                    val file = files.getJSONObject(i)
                    fileNames.add(file.getString("filename"))
                }
                return fileNames
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}