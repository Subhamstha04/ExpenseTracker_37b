package com.example.transaction.add_trans2

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// --- Data class for each expense item ---
data class ExpenseItem(
    val name: String = "",
    val price: String = ""
)

// --- ViewModel ---
class CategorizationViewModel : ViewModel() {

    // Initialize 12 empty items
    var items by mutableStateOf(List(12) { ExpenseItem() })
        private set

    // Update a specific item
    fun updateItem(index: Int, name: String, price: String) {
        val trimmedName = name.trim()
        val trimmedPrice = price.trim()
        items = items.toMutableList().also {
            it[index] = ExpenseItem(trimmedName, trimmedPrice)
        }
    }
}
