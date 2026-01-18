package com.example.transaction.add_trans1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transaction.R

@Composable
fun AddTrans1Screen(
    viewModel: AddTrans1ViewModel = viewModel(),
    onPlanClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val monthlyIncome by viewModel.monthlyIncome.collectAsState()
    val dailyIncome by viewModel.dailyIncome.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with salary
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B))
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

        // Show error if any
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Back button
        IconButton(onClick = onBackClick, modifier = Modifier.padding(16.dp)) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_previous),
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Piggy bank image
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
                    modifier = Modifier.size(180.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Plan button
        Button(
            onClick = onPlanClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
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
