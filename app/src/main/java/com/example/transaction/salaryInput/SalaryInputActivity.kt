package com.example.transaction.salaryInput

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R
import com.example.transaction.add_trans1.AddTrans1Activity

class SalaryInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary_input)

        val etMonthly = findViewById<EditText>(R.id.etMonthly)
        val etPerDay = findViewById<EditText>(R.id.etPerDay)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val monthlyText = etMonthly.text.toString().trim()
            val perDayText = etPerDay.text.toString().trim()
            if (monthlyText.isEmpty()) {
                etMonthly.error = "Enter monthly salary"
                return@setOnClickListener
            }
            if (perDayText.isEmpty()) {
                etPerDay.error = "Enter per day salary"
                return@setOnClickListener
            }

            val monthly = monthlyText.toDoubleOrNull()
            val perDay = perDayText.toDoubleOrNull()

            if (monthly == null || monthly <= 0) {
                etMonthly.error = "Enter a valid number"
                return@setOnClickListener
            }

            if (perDay == null || perDay <= 0) {
                etPerDay.error = "Enter a valid number"
                return@setOnClickListener
            }

            // Navigate to AddTrans1Activity with salary data
            val intent = Intent(this, AddTrans1Activity::class.java).apply {
                putExtra("monthly", monthly)
                putExtra("perDay", perDay)
            }
            startActivity(intent)
        }
    }
}
