package com.example.transaction.salaryInput

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Just a minimal layout for now
        val tv = TextView(this)
        tv.text = "Category Screen (Coming Soon)"
        setContentView(tv)
    }
}
