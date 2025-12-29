package com.example.expensetrackerishan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetrackerishan.dashboard.DashboardScreen
import com.example.expensetrackerishan.dashboard.DashboardViewModel
import com.example.expensetrackerishan.ui.theme.ExpenseTrackerIshanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerIshanTheme {
                // Display the DashboardScreen
                DashboardScreen()
            }
        }
    }
}
