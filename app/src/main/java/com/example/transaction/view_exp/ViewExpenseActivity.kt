package com.example.transaction.view_exp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class ViewExpenseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background // or Color(0xFFB3E5FC) if you want light blue
                ) {
                    ViewExpenseScreen() // Compose screen showing expenses
                }
            }
        }
    }
}
