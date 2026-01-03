package com.example.expensetrackerishan

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.expensetrackerishan.transaction.SalaryInputActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Directly launch SalaryInputActivity
        startActivity(Intent(this, SalaryInputActivity::class.java))

        // Optional: close MainActivity so back button doesn't return here
        finish()
    }
}
