package com.kotlinstudy.kotlin_pydio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import kotlin.collections.joinToString


class MainActivity : AppCompatActivity() {

    private lateinit var fileTextView: TextView
    private lateinit var server: MyServerPydio
    private val accessToken = "MY_ACCESS_TOKEN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fileTextView = findViewById(R.id.text_view)
        server = MyServerPydio()

        // 코루틴 빌더 사용
        GlobalScope.launch(Dispatchers.Main) {
            val files = withContext(Dispatchers.IO) {
                server.getFiles(accessToken)
            }

            Handler(Looper.getMainLooper()).post {
                if (files != null) {
                    fileTextView.text = files.joinToString("\n")
                } else {
                    fileTextView.text = "Failed to fetch files"
                }
            }
        }
    }
}