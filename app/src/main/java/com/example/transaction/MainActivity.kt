package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch MainMenuActivity
        val intent = Intent(this, com.example.transaction.mainMenu.MainMenuActivity::class.java)
        startActivity(intent)
        finish() // close MainActivity so back button doesn't return here
    }
}
