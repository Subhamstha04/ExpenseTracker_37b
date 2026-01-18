package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch Dashboard first
        startActivity(Intent(this, com.example.transaction.dashboard.DashboardActivity::class.java))

        // Close launcher so back button won't return here
        finish()
    }
}
