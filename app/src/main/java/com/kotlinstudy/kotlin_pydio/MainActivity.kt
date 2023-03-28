package com.kotlinstudy.kotlin_pydio

import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {

    private lateinit var databaseManager: DatabaseManager
    private lateinit var tvdb: TextView

    /*
    private lateinit var fileTextView: TextView
    private lateinit var server: MyServerPydio
    private val accessToken = "MY_ACCESS_TOKEN"
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.getInstance()

        val server = retrofit.create(UserAPI::class.java)
        val useremail = "user@example.com"

        server.getUser("useremail").enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                Log.d(TAG, "성공 : ${response.body()}")
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(TAG, "실패 : ${t.localizedMessage}")
            }
        })

        /*
        tvdb = findViewById(R.id.tv_db)
        fileTextView = findViewById(R.id.text_view)
        //server = MyServerPydio()

        lifecycleScope.launch {
            databaseManager = withContext(Dispatchers.IO) { DatabaseManager() }
            val data = databaseManager.fetchData()
            // 가져온 데이터를 TextView에 보여줌
            tvdb.text = data.joinToString("\n")
        }
        */


        /*
        // 코루틴 빌더 사용
        GlobalScope.launch(Dispatchers.Main) {

            val files = withContext(Dispatchers.IO) {
                server.getFiles(accessToken)
            }

            if (files != null) {
                fileTextView.text = files.joinToString("\n")

                for (i: Int in 1..3)
                    println("Log!! file: " + files.get(i).toString())
            } else {
                fileTextView.text = "Failed to fetch files"
                println("Log files: No files")
            }
        }
        */



    }

    /*
    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeConnection()
    }
    */
}