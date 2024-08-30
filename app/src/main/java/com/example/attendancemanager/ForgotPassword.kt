package com.example.attendancemanager

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.attendancemanager.databinding.ActivityForgotBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity(){
    private lateinit var etpassword : EditText
    private lateinit var btnResetPassword : Button
    private lateinit var binding: ActivityForgotBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        setContentView(binding.root)
        etpassword = findViewById(R.id.forgotEmail)
        btnResetPassword = findViewById(R.id.ResetButton)

        auth=FirebaseAuth.getInstance()


        binding.ResetButton.setOnClickListener {
            val sPassword = etpassword.text.toString()
            auth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {
                    Toast.makeText(this ,"Please Check Your E-mail",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }
        binding.loginRedirectText.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        binding.cancelButton.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

}