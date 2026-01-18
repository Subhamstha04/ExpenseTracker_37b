package com.example.transaction.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val welcomeTitle: String = "WELCOME",
    val mainText: String = "Start recording transactions and make your financial planning simple, quick and efficient.",
    val buttonText: String = "LOGIN/REGISTER",
    val bannerText: String = "TRACK FINANCIAL SERVICES"
)

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    // Called when login/register button is clicked
    fun onLoginClicked() {
        // Handle navigation or other logic here
    }
}