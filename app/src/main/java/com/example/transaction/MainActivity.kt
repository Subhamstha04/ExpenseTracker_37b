package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.graphHistory.GraphActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch GraphActivity
        val intent = Intent(this, GraphActivity::class.java)
        startActivity(intent)

        // Close MainActivity so user can't go back to it
        finish()
    }
}
