package com.example.bataillenavale

import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var tvInstruction: TextView
    private var difficulty: String = ""
    private var gridSize: Int = 0
    private var isPlacingShips = true
    private var shipPosition: Int = -1
    private var guesses: Int = 0
    private val maxGuesses = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gridView = findViewById(R.id.gridView)
        tvInstruction = findViewById(R.id.tv_instruction)

        difficulty = intent.getStringExtra("DIFFICULTY") ?: "easy"
        gridSize = when (difficulty) {
            "easy" -> 6
            "medium" -> 8
            "hard" -> 10
            else -> 6
        }

        initializeGrid()
    }

    private fun initializeGrid() {
        val adapter = GridAdapter(this, gridSize)
        gridView.numColumns = gridSize
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            if (isPlacingShips) {
                if (shipPosition == -1) {
                    adapter.setItem(position, R.drawable.boat)
                    shipPosition = position
                    isPlacingShips = false
                    tvInstruction.text = "Trouvez le bateau caché"
                    adapter.hideShips()
                }
            } else {
                guesses++
                if (position == shipPosition) {
                    adapter.setItem(position, R.drawable.hit)
                    tvInstruction.text = "Vous avez gagné!"
                    gridView.isEnabled = false
                } else {
                    adapter.setItem(position, R.drawable.miss)
                }
                if (guesses >= maxGuesses && position != shipPosition) {
                    tvInstruction.text = "Vous avez perdu!"
                    gridView.isEnabled = false
                }
            }
        }
    }
}
