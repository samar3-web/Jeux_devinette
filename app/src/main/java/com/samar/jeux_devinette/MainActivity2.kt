package com.samar.jeux_devinette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //start login activity after 2sec
        Handler().postDelayed({

                //user not logged in start activity
                startActivity(Intent(this@MainActivity2, MainActivity::class.java))
                finish()
            },3000)
    }
}