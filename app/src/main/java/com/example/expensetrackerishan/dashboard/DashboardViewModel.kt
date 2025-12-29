package com.example.expensetrackerishan.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DashboardUiState(
    val welcomeMessage: String = "WELCOME",
    val tagline: String = "Start recording transactions and make your financial planning simple. Quick and efficient.",
    val bannerText: String = "TRACK FINANCIAL SERVICES"
)

class DashboardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun onLoginClicked() {
        // Handle login/register click
    }
}
