package com.example.transaction

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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

            if (monthly.isEmpty() || perDay.isEmpty()) {
                if (monthly.isEmpty()) etMonthly.error = "Enter monthly salary"
                if (perDay.isEmpty()) etPerDay.error = "Enter per day salary"
                return@setOnClickListener
            }

            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("monthly", monthly)
            intent.putExtra("perDay", perDay)
            startActivity(intent)
        }
    }
}
