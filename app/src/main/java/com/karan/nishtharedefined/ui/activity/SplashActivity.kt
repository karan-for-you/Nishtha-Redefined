package com.karan.nishtharedefined.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        pauseSplashThenMove()
    }

    private fun pauseSplashThenMove() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    )
                )
            }, AppConstants.SPLASH_HALT
        )
    }

}