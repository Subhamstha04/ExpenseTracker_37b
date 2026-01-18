package com.example.transaction.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Holds all the UI data for the Dashboard screen
data class DashboardUiState(
    val welcomeTitle: String = "WELCOME",
    val mainText: String = "Start recording transactions and make your financial planning simple, quick and efficient.",
    val buttonText: String = "LOGIN/REGISTER",
    val bannerText: String = "TRACK FINANCIAL SERVICES"
)

class DashboardViewModel : ViewModel() {

    // Internal mutable state
    private val _uiState = MutableStateFlow(DashboardUiState())

    // Exposed immutable state
    val uiState: StateFlow<DashboardUiState> = _uiState

    /**
     * Handles the Login/Register button click.
     * Since navigation should be done in Activity or Composable layer,
     * we provide a lambda callback from the UI layer.
     */
    fun onLoginClicked(navigateToLogin: () -> Unit) {
        // You can do any pre-navigation logic here if needed
        navigateToLogin()
    }
}
