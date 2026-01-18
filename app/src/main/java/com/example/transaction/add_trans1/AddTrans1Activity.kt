package com.example.transaction.add_trans1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transaction.add_trans2.CategorizationActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class AddTrans1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AddTrans1ViewModel = viewModel()

            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AddTrans1Screen(
                        viewModel = viewModel,
                        onPlanClick = {
                            // Navigate to CategorizationActivity
                            startActivity(Intent(this, CategorizationActivity::class.java))
                        },
                        onBackClick = { finish() }
                    )
                }
            }
        }
    }
}
