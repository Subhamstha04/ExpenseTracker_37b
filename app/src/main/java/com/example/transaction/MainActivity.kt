package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start SalaryInputActivity immediately
        val intent = Intent(this, SalaryInputActivity::class.java)
        startActivity(intent)
        finish() // optional, so user cannot go back to this blank activity
    }
}
