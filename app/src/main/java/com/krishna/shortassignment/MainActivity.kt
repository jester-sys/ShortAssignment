package com.krishna.shortassignment

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout
    private lateinit var mediaPlayer: MediaPlayer
    private val numberOfBalloons = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.container)
        mediaPlayer = MediaPlayer.create(this, R.raw.burst_sound)

        repeat(numberOfBalloons) {
            addBalloonToScreen()
        }

    }

    private fun addBalloonToScreen() {
        val inflater = LayoutInflater.from(this)
        val balloon = inflater.inflate(R.layout.balloon_item, container, false) as ImageView

        val random = Random()
        val isBlue = random.nextBoolean()
        balloon.setImageResource(if (isBlue) R.drawable.balloon_blue else R.drawable.balloon_red)
        balloon.tag = isBlue

        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels
        val randomX = random.nextInt(screenWidth - balloon.width)
        val randomY = random.nextInt(screenHeight - balloon.height)

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.leftMargin = randomX
        layoutParams.topMargin = randomY
        balloon.layoutParams = layoutParams


        balloon.setOnClickListener {
            val isBalloonBlue = it.tag as Boolean
            if (isBalloonBlue) {
                playBurstSound()
                container.removeView(it)



                Handler().postDelayed({
                    addBalloonToScreen()
                }, 1000)

                showRandomToast()

            } else {
                    playBurstSound()
                    container.removeView(it)
                Handler().postDelayed({
                    addBalloonToScreen()
                }, 1000)
                showRandomToast()
            }
        }

        val balloonRise = AnimationUtils.loadAnimation(this, R.anim.balloon_rise)
        balloon.startAnimation(balloonRise)

        container.addView(balloon)
    }

    private fun playBurstSound() {
        mediaPlayer.start()
    }

    private fun showRandomToast() {
        val balloonColors = arrayOf("Blue", "Red")
        val randomColor = balloonColors[Random().nextInt(balloonColors.size)]
        Toast.makeText(this, "Next balloon color: $randomColor", Toast.LENGTH_SHORT).show()
    }


}
