package com.example.expensetrackerishan.transaction

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SalaryViewModel : ViewModel() {

    private val _monthlyError = MutableStateFlow<String?>(null)
    val monthlyError: StateFlow<String?> = _monthlyError

    private val _perDayError = MutableStateFlow<String?>(null)
    val perDayError: StateFlow<String?> = _perDayError

    private val _canProceed = MutableStateFlow(false)
    val canProceed: StateFlow<Boolean> = _canProceed

    fun validateSalaryInput(monthlyInput: String, perDayInput: String) {
        var valid = true

        if (monthlyInput.isBlank()) {
            _monthlyError.value = "Enter monthly salary"
            valid = false
        } else {
            _monthlyError.value = null
        }

        if (perDayInput.isBlank()) {
            _perDayError.value = "Enter per-day salary"
            valid = false
        } else {
            _perDayError.value = null
        }

        _canProceed.value = valid
    }
}