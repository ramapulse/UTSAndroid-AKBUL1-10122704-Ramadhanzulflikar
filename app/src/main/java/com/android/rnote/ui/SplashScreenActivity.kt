package com.android.rnote.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.rnote.R
import com.android.rnote.data.database.AppDatabase


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        AppDatabase.getDatabase(this).profileDao().getProfileById().observe(this){

        }


        Handler().postDelayed(Runnable {
            val intent = Intent(
                this@SplashScreenActivity,
                WalkthroughActivity::class.java
            )
            startActivity(intent)
            finish()
        }, 2500)
    }
}