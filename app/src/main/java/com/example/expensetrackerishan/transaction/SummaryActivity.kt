package com.example.expensetrackerishan.transaction

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetrackerishan.R

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val monthlySalaryText: TextView = findViewById(R.id.tvMonthlySalary)
        val perDaySalaryText: TextView = findViewById(R.id.tvPerDaySalary)

        // Get data from Intent
        val monthlySalary = intent.getDoubleExtra("MONTHLY_SALARY", 0.0)
        val perDaySalary = intent.getDoubleExtra("PER_DAY_SALARY", 0.0)

        // Display values
        monthlySalaryText.text = "Monthly Salary: ₹$monthlySalary"
        perDaySalaryText.text = "Per Day Salary: ₹$perDaySalary"
    }
}
