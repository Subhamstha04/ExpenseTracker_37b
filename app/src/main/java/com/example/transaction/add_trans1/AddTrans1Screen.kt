package com.example.transaction.add_trans1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transaction.R

@Composable
fun AddTrans1Screen(
    viewModel: AddTrans1ViewModel,
    onBackClick: () -> Unit,
    onPlanClick: () -> Unit // ✅ Added this parameter for navigation
) {
    // Collect state from ViewModel
    val monthlyIncome by viewModel.monthlyIncome.collectAsState()
    val dailyIncome by viewModel.dailyIncome.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)) // Light sky-blue
    ) {

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B)) // Yellow
                .padding(28.dp)
        ) {
            Text(
                text = "Your Monthly Income: $monthlyIncome",
                fontSize = 21.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Your Per Day Income: $dailyIncome",
                fontSize = 21.sp
            )
        }

        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_previous),
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Piggy Bank Box
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.piggy_pic),
                    contentDescription = "Piggy Bank",
                    modifier = Modifier.size(width = 350.dp, height = 400.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // "What's your plan today?" Button
        Button(
            onClick = onPlanClick, // ✅ Calls the lambda passed from Activity
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .offset(y = (-190).dp) // Moves button up
                .height(76.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8D4A3A)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "What's your plan today?",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
