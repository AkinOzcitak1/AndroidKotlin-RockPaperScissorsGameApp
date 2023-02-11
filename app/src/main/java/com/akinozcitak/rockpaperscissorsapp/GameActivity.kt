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
        userClicked = true
        binding.imageviewPaperUser.visibility = View.INVISIBLE
        binding.imageviewScissorsUser.visibility = View.INVISIBLE
        binding.imageviewRockUser.visibility = View.VISIBLE
        userChoice = "rock"
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice + "userClickRock - CompChoice")
        if (compChoice == "rock") {
            game("rock","rock")
            println("rock" + "userClickRock - IfCompChoice==rock")
        }
        if (compChoice == "paper") {
            game("rock","paper")
            println("paper" + "userClickRock - IfCompChoice==paper")
        }
        if (compChoice == "scissors") {
            game("rock","scissors")
            println("scis" + "userClickRock - IfCompChoice==scissors")
        }



    }
    fun userClickPaper (view : View) {
        userClicked = true
        binding.imageviewRockUser.visibility = View.INVISIBLE
        binding.imageviewScissorsUser.visibility = View.INVISIBLE
        binding.imageviewPaperUser.visibility = View.VISIBLE
        userChoice = "paper"
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice)
        if (compChoice == "rock") {
            game("paper","rock")
            println("rock")
        }
        if (compChoice == "paper") {
            game("paper","paper")
            println("paper")
        }
        if (compChoice == "scissors") {
            game("paper","scissors")
            println("scis")
        }
    }
    fun userClickScissors (view : View) {
        userClicked = true
        binding.imageviewPaperUser.visibility = View.INVISIBLE
        binding.imageviewRockUser.visibility = View.INVISIBLE
        binding.imageviewScissorsUser.visibility = View.VISIBLE
        userChoice = "scissors"
        val random = Random()
        val randomIndex = random.nextInt(3)
        compChoice = choiceArrayC[randomIndex]
        println(compChoice)
        if (compChoice == "rock") {
            game("scissors","rock")
            println("rock")
        }
        if (compChoice == "paper") {
            game("scissors","paper")
            println("paper")
        }
        if (compChoice == "scissors") {
            game("scissors","scissors")
            println("scis")
        }
    }

    fun animateCompImages() {
        runnable = object : Runnable {
            override fun run() {
                if (userClicked == false) {
                for (image in imageArrayC) {
                    image.visibility = View.INVISIBLE
                }



                    val random = Random()
                    val randomIndex = random.nextInt(3)
                    imageArrayC[randomIndex].visibility = View.VISIBLE
                    handler.postDelayed(runnable, 200)
                }
            }
        }
        handler.post(runnable)

    }

    fun showAll() {
        binding.imageviewScissorsUser.visibility = View.VISIBLE
        binding.imageviewRockUser.visibility = View.VISIBLE
        binding.imageviewPaperUser.visibility = View.VISIBLE
    }


    fun game(user : String, comp : String) {

        if(user == "rock" && comp == "scissors") {
            binding.imageviewScissorsComp.visibility = View.VISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            gameScore = gameScore + 1
            Toast.makeText(this@GameActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({userClicked = false}, 2000)
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
            Handler().postDelayed({showAll()}, 3000)
            }


        else if(user == "paper" && comp == "rock") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.INVISIBLE
            binding.imageviewRockComp.visibility = View.VISIBLE
            gameScore = gameScore + 1
            Toast.makeText(this@GameActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({userClicked = false}, 2000)
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
            Handler().postDelayed({showAll()}, 3000)
        }
        else if(user == "scissors" && comp == "paper") {
            binding.imageviewScissorsComp.visibility = View.INVISIBLE
            binding.imageviewPaperComp.visibility = View.VISIBLE
            binding.imageviewRockComp.visibility = View.INVISIBLE
            gameScore = gameScore + 1
            Toast.makeText(this@GameActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({userClicked = false}, 2000)
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
            Handler().postDelayed({showAll()}, 3000)
        }
        else if(user.equals(comp)) {
            if (comp == "rock"){
                binding.imageviewScissorsComp.visibility = View.INVISIBLE
                binding.imageviewPaperComp.visibility = View.INVISIBLE
                binding.imageviewRockComp.visibility = View.VISIBLE
            }
            if (comp == "paper"){
                binding.imageviewScissorsComp.visibility = View.INVISIBLE
                binding.imageviewPaperComp.visibility = View.VISIBLE
                binding.imageviewRockComp.visibility = View.INVISIBLE
            }
            if (comp == "scissors"){
                binding.imageviewScissorsComp.visibility = View.VISIBLE
                binding.imageviewPaperComp.visibility = View.INVISIBLE
                binding.imageviewRockComp.visibility = View.INVISIBLE
            }
            Toast.makeText(this@GameActivity, "DRAW!!!!!!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({userClicked = false}, 2000)
            Handler().postDelayed({animateCompImages()}, 3000)
            binding.userScore.text = gameScore.toString()
            Handler().postDelayed({showAll()}, 3000)
        }
        else{
            val alert = AlertDialog.Builder(this@GameActivity)
            alert.setTitle("Game Over")
            alert.setMessage("Restart The Game?")
            alert.setPositiveButton("Yes") {dialog, which ->
                //Restart
                showAll()
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
