package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.mainMenu.MainMenuActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch Main Menu screen
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish() // Close MainActivity so user can't return here
    }
}
