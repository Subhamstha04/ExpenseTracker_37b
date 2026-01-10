package com.example.transaction.view_exp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewExpenseViewModel {

    // Total money (0 for now)
    var totalMoney by mutableStateOf(0)

    // Empty list for now
    var expenses by mutableStateOf(listOf<Expense>())

    data class Expense(
        val title: String,
        val category: String,
        val date: String,
        val amount: Int
    )
}
