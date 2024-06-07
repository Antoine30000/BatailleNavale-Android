package com.example.bataillenavale

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEasy: Button = findViewById(R.id.btn_easy)
        val btnMedium: Button = findViewById(R.id.btn_medium)
        val btnHard: Button = findViewById(R.id.btn_hard)

        btnEasy.setOnClickListener { startGame("easy") }
        btnMedium.setOnClickListener { startGame("medium") }
        btnHard.setOnClickListener { startGame("hard") }
    }

    private fun startGame(difficulty: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("DIFFICULTY", difficulty)
        startActivity(intent)
    }
}
