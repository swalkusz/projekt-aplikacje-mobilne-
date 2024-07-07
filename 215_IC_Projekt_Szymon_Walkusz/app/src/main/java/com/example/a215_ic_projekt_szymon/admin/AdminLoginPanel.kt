package com.example.a215_ic_projekt_szymon.admin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a215_ic_projekt_szymon.R

class AdminLoginPanel : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_login_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backFromAdminBtn: Button = findViewById(R.id.backFromAdminBtn)
        val loginBtn: Button = findViewById(R.id.logInBtn)
        val loginField: EditText = findViewById(R.id.login)
        val passwodField: EditText = findViewById(R.id.password)
        backFromAdminBtn.setOnClickListener {
            finish()
        }
        loginBtn.setOnClickListener {
            if (loginField.text.toString()=="admin" && passwodField.text.toString()=="pass") {
                val intent = Intent(this, AdminPanel::class.java)
                startActivity(intent)
//                Toast.makeText(applicationContext,"zalogowano",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Login lub hasło są nieprawidłowe", Toast.LENGTH_LONG).show()
            }
        }
    }
}