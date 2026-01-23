package com.example.transaction.graphHistory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GraphHistoryScreen(
    viewModel: GraphHistoryViewModel = viewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadExpenses()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔍 DEBUG LINE — PUT IT HERE
        Text(
            text = "Expense count: ${expenses.size}",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Bar Graph", fontSize = 20.sp)
        BarGraph(
            expenses = expenses,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Line Graph", fontSize = 20.sp)
        LineGraph(
            expenses = expenses,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("History", fontSize = 20.sp)

        error?.let {
            Text(it, color = androidx.compose.ui.graphics.Color.Red)
        }

        LazyColumn {
            items(expenses) { expense ->
                Text(
                    text = "• ${expense.name}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
