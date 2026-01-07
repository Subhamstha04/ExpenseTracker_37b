package com.example.transaction.salaryInput

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R


class SalaryInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary_input)

        val etMonthly = findViewById<EditText>(R.id.etMonthly)
        val etPerDay = findViewById<EditText>(R.id.etPerDay)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val monthly = etMonthly.text.toString()
            val perDay = etPerDay.text.toString()

            // Simple validation
            if (monthly.isEmpty()) etMonthly.error = "Enter monthly salary"
            if (perDay.isEmpty()) etPerDay.error = "Enter per day salary"
            if (monthly.isEmpty() || perDay.isEmpty()) return@setOnClickListener

            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("monthly", monthly)
            intent.putExtra("perDay", perDay)
            startActivity(intent)
        }
    }
}
