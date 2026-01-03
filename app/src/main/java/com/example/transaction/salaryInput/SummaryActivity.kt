package com.example.transaction.salaryInput

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val tvMonthly = findViewById<TextView>(R.id.tvMonthly)
        val tvPerDay = findViewById<TextView>(R.id.tvPerDay)
        val btnPlan = findViewById<Button>(R.id.btnPlan)

        val monthly = intent.getStringExtra("monthly") ?: "0"
        val perDay = intent.getStringExtra("perDay") ?: "0"

        tvMonthly.text = getString(R.string.monthly_income, monthly)
        tvPerDay.text = getString(R.string.per_day_income, perDay)

        btnPlan.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }
}
