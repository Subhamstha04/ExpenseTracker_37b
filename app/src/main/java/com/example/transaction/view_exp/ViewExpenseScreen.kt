package com.example.transaction.view_exp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ViewExpenseScreen(viewModel: ViewExpenseViewModel = remember { ViewExpenseViewModel() }) {

    // Light blue background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)) // Light Blue
            .padding(16.dp)
    ) {

        // Money card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = "Money: ₹ ${viewModel.totalMoney}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Expense list or empty state
        if (viewModel.expenses.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No expenses yet", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn {
                items(viewModel.expenses) { expense ->
                    ExpenseItem(expense)
                }
            }
        }
    }
}
