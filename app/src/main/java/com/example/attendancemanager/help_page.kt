package com.example.attendancemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class help_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page)
    }
    override fun onBackPressed() {
        val intentmain= Intent(this,MainActivity::class.java)
        startActivity(intentmain)
        finish()
    }

}