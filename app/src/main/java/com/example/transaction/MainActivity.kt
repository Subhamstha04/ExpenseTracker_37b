package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.salaryInput.SalaryInputActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch the Salary Input screen directly
        startActivity(Intent(this, SalaryInputActivity::class.java))
        finish() // Optional: close MainActivity if you don't need it
    }
}
