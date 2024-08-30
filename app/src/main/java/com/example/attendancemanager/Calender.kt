package com.example.attendancemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.ActionBar
import java.util.Calendar


class Calender : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var calendar: Calendar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView=findViewById(R.id.calendarview)
        calendar=Calendar.getInstance()


    }
    override fun onBackPressed() {
        val intentmain= Intent(this,MainActivity::class.java)
        startActivity(intentmain)
        finish()
    }



}




