package com.example.transaction.view_exp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ViewExpenseScreen(
    viewModel: ViewExpenseViewModel = viewModel()
) {
    val total by viewModel.totalExpense.collectAsState()
    val items by viewModel.itemNames.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadExpenses()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {

        // 🔝 TOTAL EXPENSE
        Text(
            text = "Total Expense: ₹$total",
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        error?.let {
            Text(text = it, color = Color.Red)
        }

        // 📋 ITEM NAMES
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { name ->
                Text(
                    text = "• $name",
                    fontSize = 18.sp
                )
            }
        }
    }
}
