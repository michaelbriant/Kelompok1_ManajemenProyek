package com.example.promanagement.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.promanagement.R

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val btnTeam = findViewById<Button>(R.id.btnTeam)
        val btnProyek = findViewById<Button>(R.id.btnProyek)
        val btnView = findViewById<Button>(R.id.btnView)

        btnTeam.setOnClickListener {
            val intent = Intent(this, TeamForm::class.java)
            startActivity(intent)


        }
    }
}