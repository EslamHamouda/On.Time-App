package com.example.ontime

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var isFirstRun:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opening_app)
        Handler().postDelayed({moveToStart()},1500)

    }

    private fun moveToStart() {
         isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun",true)
        if (isFirstRun) {
            startActivity(Intent(this@MainActivity, LoginScreen::class.java))
        }
        else {
            val intent = Intent(this, SchedulePage::class.java)
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle()
            )
            finish()
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).apply()

    }

}

