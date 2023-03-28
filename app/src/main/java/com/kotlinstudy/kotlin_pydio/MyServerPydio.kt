package com.kotlinstudy.kotlin_pydio

import com.kotlinstudy.kotlin_pydio.Ignore_SSL.getUnsafeOkHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface MyServerPydioService {
    @GET("files")
    suspend fun getFiles(@Header("Authorization") token: String): List<String>
}

class MyServerPydio {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://192.168.0.6:8080/")
        .client(getUnsafeOkHttpClient().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MyServerPydioService::class.java)

    suspend fun getFiles(token: String): List<String>? {
        return withContext(Dispatchers.IO) {
            try {
                service.getFiles("Bearer &token")
            } catch (e: Exception) {
                null
            }
        }
    }
}

