package com.example.transaction.add_trans1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.transaction.add_trans2.CategorizationActivity

class AddTrans1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get salary from intent
        val monthly = intent.getDoubleExtra("monthly", 0.0)
        val perDay = intent.getDoubleExtra("perDay", 0.0)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AddTrans1Screen(
                        monthlyIncome = monthly,
                        dailyIncome = perDay,
                        onPlanClick = {
                            // Navigate to AddTrans2 (CategorizationActivity)
                            startActivity(Intent(this, CategorizationActivity::class.java))
                        },
                        onBackClick = { finish() }
                    )
                }
            }
        }
    }
}
