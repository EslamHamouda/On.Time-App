package com.example.ontime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

    }

    fun startButton(view: View) {
        val intent=Intent(this,SchedulePage::class.java)
        startActivity(intent)
        finish()
    }
}