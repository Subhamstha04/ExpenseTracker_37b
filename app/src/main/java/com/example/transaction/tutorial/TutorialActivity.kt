package com.example.transaction.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TutorialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TutorialScreen(
                    onBack = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Tutorial") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            SectionTitle("📌 What this app does")
            SectionText(
                "This app helps you track income and expenses, categorize transactions, " +
                        "and analyze your spending using graphs."
            )

            SectionTitle("➕ Add Transaction")
            SectionText(
                "• Enter your salary\n" +
                        "• Add daily expenses\n" +
                        "• Transactions are saved securely"
            )

            SectionTitle("📂 Categorization")
            SectionText(
                "• Expenses are grouped by category\n" +
                        "• Helps with better tracking\n" +
                        "• Used for analysis and graphs"
            )

            SectionTitle("📊 Graph & History")
            SectionText(
                "• View spending patterns\n" +
                        "• Analyze where money goes\n" +
                        "• Helps improve budgeting"
            )

            SectionTitle("👀 View Expenses")
            SectionText(
                "• See all recorded expenses\n" +
                        "• Review transaction history anytime"
            )

            SectionTitle("🔐 Security")
            SectionText(
                "• Data stored securely using Firebase\n" +
                        "• Login required to access data\n" +
                        "• Password recovery supported"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Got it 👍")
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4CAF50),
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SectionText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )
}
