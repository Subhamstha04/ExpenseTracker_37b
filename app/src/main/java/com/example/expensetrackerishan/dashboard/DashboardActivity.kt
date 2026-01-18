package com.example.expensetrackerishan.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.expensetrackerishan.ui.theme.ExpenseTrackerIshanTheme

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpenseTrackerIshanTheme {
                DashboardScreen()
            }
        }
    }
}
