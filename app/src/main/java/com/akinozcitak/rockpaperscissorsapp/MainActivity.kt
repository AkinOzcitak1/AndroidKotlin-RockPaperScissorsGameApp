package com.akinozcitak.rockpaperscissorsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akinozcitak.rockpaperscissorsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    var highestScoreFromPreferences: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //SharedPrefences Initialize
        sharedPreferences = this.getSharedPreferences("com.akinozcitak.rockpaperscissorsapp",
            Context.MODE_PRIVATE)

        highestScoreFromPreferences = sharedPreferences.getInt("highestScore",0)

        if (highestScoreFromPreferences == 0) {
            binding.highestScoreTextView.text = "Your Top Score of All Time: "
        } else {
            binding.highestScoreTextView.text = "Your Record of All Time: $highestScoreFromPreferences"
        }

    }


    fun nextIntent(view: View) {
        println("next")
        sharedPreferences = this.getSharedPreferences("com.akinozcitak.rockpaperscissorsapp",
            Context.MODE_PRIVATE)

        highestScoreFromPreferences = sharedPreferences.getInt("highestScore",0)

        if (highestScoreFromPreferences == 0) {
            binding.highestScoreTextView.text = "Your Top Score of All Time: "
        } else {
            binding.highestScoreTextView.text = "Your Record of All Time: $highestScoreFromPreferences"
        }
        val intent = Intent(applicationContext, GameActivity::class.java)
        startActivity(intent)
        finish()
    }
}


