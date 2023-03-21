package com.kotlinstudy.kotlin_pydio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myServerPydio = MyServerPydio()
        val filesList = myServerPydio.getFiles()

        val filesTextView = findViewById<TextView>(R.id.text_view)
        filesTextView.text = filesList.joinToString("\n")
    }
}