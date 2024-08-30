package com.example.attendancemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancemanager.databinding.ActivityMainBinding
import com.example.attendancemanager.recyleritems.userdata
import com.example.attendancemanager.view.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addsBtn:Button
    private lateinit var calbtn: Button
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<userdata>
    private lateinit var userAdapter:UserAdapter
    private lateinit var database : DatabaseReference
    private lateinit var menu : ImageView
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar= supportActionBar
        actionBar?.title="Attendance Manager"
        actionBar?.subtitle="Manage your attendance"
        super.onCreate(savedInstanceState)
        firebaseAuth=FirebaseAuth.getInstance()
        setContentView(R.layout.activity_main)
        replaceFragment(Home())
        userList= arrayListOf(userdata())
        userAdapter = UserAdapter()
        val presentbutton = findViewById<Button>(R.id.presentbtn)
        addsBtn = findViewById(R.id.addsubject)
        addsBtn.setOnClickListener { addInfo() }
        calbtn = findViewById(R.id.calendarbtn)
        calbtn.setOnClickListener() {
            val calintent = Intent(this, Calender::class.java)
            startActivity(calintent)
            finish()
        }}
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.actionbar_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem) : Boolean{
            when (item.itemId) {
                R.id.about -> {
                    val intentabout = Intent(this, about_activity::class.java)
                    startActivity(intentabout)
                    finish()
                }

                R.id.help -> {
                    val intenthelp = Intent(this, help_page::class.java)
                    startActivity(intenthelp)
                    finish()
                }

                R.id.signout -> {
                    firebaseAuth.signOut()
                    val intentlogout= Intent(this,LoginActivity::class.java)
                    startActivity(intentlogout)
                    finish()
                }
            }
            return true
        }

    private fun addInfo() {
        var firebaseAuth = FirebaseAuth.getInstance()
        var userid = firebaseAuth.currentUser!!.uid
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.dialog_box,null)
        val userName1 = v.findViewById<EditText>(R.id.subjectnamed)
        val userNo = v.findViewById<EditText>(R.id.totalclasses)
        val present = v.findViewById<EditText>(R.id.present)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("ADD"){ dialog,_->
            val names = userName1.text.toString()
            val number = userNo.text.toString()
            val present = present.text.toString()
            val percent = (present.toFloat()/number.toFloat())*100.0f
            Log.d("SOHAN","$percent")
            userList.add(userdata("$names","Total Classes : $number","Present=$present",percent))
            userAdapter.notifyDataSetChanged()
            var userid = FirebaseAuth.getInstance().currentUser!!.uid
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            database = FirebaseDatabase.getInstance().getReference("Users/")
            val User = userdata(subject=names,present=present,totalclasses=number, progressbar = percent)
            database.child(userid).child(names).setValue(User).addOnSuccessListener{
                    Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show() }
            dialog.dismiss() }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }
}
