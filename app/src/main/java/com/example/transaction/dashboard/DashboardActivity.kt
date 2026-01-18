package com.example.transaction.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.transaction.login.LoginActivity

class DashboardActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            // Pass lambda for the Login/Register button
            DashboardScreen(
                uiState = uiState,
                onLoginClicked = {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            )
        }
    }
}
