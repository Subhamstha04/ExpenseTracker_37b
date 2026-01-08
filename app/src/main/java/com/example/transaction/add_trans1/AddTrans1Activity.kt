package com.example.transaction.add_trans1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel

class AddTrans1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ✅ Use Material3 theme directly, no custom theme
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: AddTrans1ViewModel = viewModel()
                    AddTrans1Screen(
                        viewModel = viewModel,
                        onBackClick = { finish() } // back button handling
                    )
                }
            }
        }
    }
}
