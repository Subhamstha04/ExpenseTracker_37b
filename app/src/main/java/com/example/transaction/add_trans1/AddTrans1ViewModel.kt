package com.example.transaction.add_trans1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddTrans1ViewModel : ViewModel() {

    // Internal mutable state
    private val _monthlyIncome = MutableStateFlow("—")
    private val _dailyIncome = MutableStateFlow("—")

    // Exposed immutable state
    val monthlyIncome: StateFlow<String> = _monthlyIncome.asStateFlow()
    val dailyIncome: StateFlow<String> = _dailyIncome.asStateFlow()

    /**
     * Update income values
     * (safe to call from Compose or XML)
     */
    fun setIncome(
        monthly: String,
        daily: String
    ) {
        _monthlyIncome.value = monthly
        _dailyIncome.value = daily
    }
}
