package com.example.transaction.salaryInput

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R

class SalaryInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary_input)

        // Find views by ID safely
        val etMonthly = findViewById<EditText>(R.id.etMonthly)
        val etPerDay = findViewById<EditText>(R.id.etPerDay)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // Null check to prevent crashes if layout IDs are missing
        if (etMonthly == null || etPerDay == null || btnNext == null) {
            Toast.makeText(this, "Layout not loaded correctly", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        btnNext.setOnClickListener {
            val monthlyText = etMonthly.text.toString().trim()
            val perDayText = etPerDay.text.toString().trim()
            var isValid = true

            // Validate empty inputs
            if (monthlyText.isEmpty()) {
                etMonthly.error = "Enter monthly salary"
                isValid = false
            }

            if (perDayText.isEmpty()) {
                etPerDay.error = "Enter per day salary"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            // Convert to Double safely
            val monthly = monthlyText.toDoubleOrNull()
            val perDay = perDayText.toDoubleOrNull()

            if (monthly == null) {
                etMonthly.error = "Enter a valid number"
                return@setOnClickListener
            }

            if (perDay == null) {
                etPerDay.error = "Enter a valid number"
                return@setOnClickListener
            }

            // Ensure positive numbers
            if (monthly <= 0) {
                Toast.makeText(this, "Monthly salary must be positive", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (perDay <= 0) {
                Toast.makeText(this, "Per day salary must be positive", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Launch SummaryActivity safely
            try {
                val intent = Intent(this, SummaryActivity::class.java).apply {
                    putExtra("monthly", monthly)
                    putExtra("perDay", perDay)
                }
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error opening Summary screen", Toast.LENGTH_LONG).show()
            }
        }
    }
}
