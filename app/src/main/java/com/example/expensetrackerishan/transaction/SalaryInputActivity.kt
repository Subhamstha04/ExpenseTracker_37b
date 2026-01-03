package com.example.expensetrackerishan.transaction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.expensetrackerishan.ui.theme.ExpenseTrackerIshanTheme

class SalaryInputActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpenseTrackerIshanTheme {
                SalaryInputScreen(
                    onContinueClicked = { monthly, perDay ->
                        navigateToSummary(monthly, perDay)
                    }
                )
            }
        }
    }

    private fun navigateToSummary(monthly: String, perDay: String) {
        val intent = Intent(this, SummaryActivity::class.java).apply {
            putExtra("monthly", monthly)
            putExtra("perDay", perDay)
        }
        startActivity(intent)
    }
}
