package com.malhotra.coffeeshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.viewmodel.ProfileViewModel

class IntroActivity : AppCompatActivity() {

    val profileViewModel  : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            profileViewModel.getData().observe(this) {
                if (it.isEmpty()) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAffinity()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }

            }
        }, 1500)



    }
}