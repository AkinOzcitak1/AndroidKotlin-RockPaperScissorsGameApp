package com.akinozcitak.rockpaperscissorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.akinozcitak.rockpaperscissorsapp.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {
    var imageArrayC = ArrayList<ImageView>()
    var choiceArrayC = ArrayList<String>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }
    var userClicked : Boolean? = null
    var userChoice : String? = null
    var compChoice : String? = null
    var gameScore = 0
    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userClicked = false

        choiceArrayC.add("rock")
        choiceArrayC.add("paper")
        choiceArrayC.add("scissors")

        imageArrayC.add(binding.imageviewRockComp)
        imageArrayC.add(binding.imageviewPaperComp)
        imageArrayC.add(binding.imageviewScissorsComp)
        animateCompImages()

    }

    fun userClickRock (view : View) {
        binding.imageviewPaperUser.visibility = View.INVISIBLE
        binding.imageviewScissorsUser.visibility = View.INVISIBLE
        userChoice = "rock"
        userClicked = true
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice + "userClickRock - CompChoice")
        if (compChoice == "rock") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.VISIBLE
            game("rock","rock")
            println("rock" + "userClickRock - IfCompChoice==rock")
        }
        if (compChoice == "paper") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.VISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("rock","paper")
            println("paper" + "userClickRock - IfCompChoice==paper")
        }
        if (compChoice == "scissors") {
            binding.imageviewScissorsComp.visibility = View.VISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("rock","scissors")
            println("scis" + "userClickRock - IfCompChoice==scissors")
        }



    }
    fun userClickPaper (view : View) {
        binding.imageviewRockUser.visibility = View.INVISIBLE
        binding.imageviewScissorsUser.visibility = View.INVISIBLE
        userClicked = true
        userChoice = "paper"
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice)
        if (compChoice == "rock") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.VISIBLE
            game("paper","rock")
            println("rock")
        }
        if (compChoice == "paper") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.VISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("paper","paper")
            println("paper")
        }
        if (compChoice == "scissors") {
            binding.imageviewScissorsComp.visibility = View.VISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("paper","scissors")
            println("scis")
        }
    }
    fun userClickScissors (view : View) {
        binding.imageviewPaperUser.visibility = View.INVISIBLE
        binding.imageviewRockUser.visibility = View.INVISIBLE
        userClicked = true
        userChoice = "scissors"
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice)
        if (compChoice == "rock") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.VISIBLE
            game("scissors","rock")
            println("rock")
        }
        if (compChoice == "paper") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.VISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("scissors","paper")
            println("paper")
        }
        if (compChoice == "scissors") {
            binding.imageviewScissorsComp.visibility = View.VISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            game("scissors","scissors")
            println("scis")
        }
    }

    fun animateCompImages() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArrayC) {
                    image.visibility = View.INVISIBLE
                }


                if (userClicked == false) {
                    val random = Random()
                    val randomIndex = random.nextInt(3)
                    imageArrayC[randomIndex].visibility = View.VISIBLE
                    handler.postDelayed(runnable, 300)
                }
            }
        }
        handler.post(runnable)

    }

    fun game(user : String, comp : String) {

        if(user == "rock" && comp == "scissors") {
            gameScore = gameScore + 1
            userClicked = false
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
        }
        else if(user == "paper" && comp == "rock") {
            gameScore = gameScore + 1
            userClicked = false
            Handler().postDelayed({animateCompImages()}, 3000)
            animateCompImages()
            binding.userScore.text = gameScore.toString()
        }
        else if(user == "scissors" && comp == "paper") {
            gameScore = gameScore + 1
            userClicked = false
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
        }
        else{
            val alert = AlertDialog.Builder(this@GameActivity)
            alert.setTitle("Game Over")
            alert.setMessage("Restart The Game?")
            alert.setPositiveButton("Yes") {dialog, which ->
                //Restart
                gameScore = 0
                userClicked = false
                binding.userScore.text = gameScore.toString()
                animateCompImages()
            }
            alert.setNegativeButton("No") {dialog, which ->
                Toast.makeText(this@GameActivity,"Game Over",Toast.LENGTH_LONG).show()
            }
            alert.show()
        }
        }

    }
