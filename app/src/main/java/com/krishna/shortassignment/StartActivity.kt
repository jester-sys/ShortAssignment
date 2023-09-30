package com.krishna.shortassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
         var playImg = findViewById<ImageView>(R.id.imageView2)
        playImg.setOnClickListener {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
        }
    }
}