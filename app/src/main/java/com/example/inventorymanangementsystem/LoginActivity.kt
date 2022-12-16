package com.example.inventorymanangementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var imgCompanyLogo1 :ImageView
    lateinit var txtCompanyName :TextView
    lateinit var btnLogin :Button
    lateinit var imgCompanyLogo :ImageView
    lateinit var txtWelcomeText :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
          title = "Inventory Management System"

        imgCompanyLogo1 = findViewById(R.id.imgCompanyLogo1)
        txtCompanyName = findViewById(R.id.txtCompanyName)
        btnLogin = findViewById(R.id.btnLogin)
        imgCompanyLogo = findViewById(R.id.imgCompanyLogo)
        txtWelcomeText = findViewById(R.id.txtWelcomeText)

        btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity,IMS2Activity::class.java)
            startActivity(intent)
        }

    }
}