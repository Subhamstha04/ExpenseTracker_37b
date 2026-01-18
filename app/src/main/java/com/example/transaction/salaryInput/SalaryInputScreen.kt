package com.example.transaction.salaryInput

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
import com.example.transaction.R

@Composable
fun SalaryInputScreen(
    onSaveAndContinue: (monthly: String, perDay: String) -> Unit,
    onContinueWithoutChange: () -> Unit
) {
    var monthly by remember { mutableStateOf("") }
    var perDay by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Salary Information",
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Image ---
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.piggy_pic),
                contentDescription = "Salary",
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- Monthly ---
        OutlinedTextField(
            value = monthly,
            onValueChange = { monthly = it },
            label = { Text("Monthly Salary") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // --- Per Day ---
        OutlinedTextField(
            value = perDay,
            onValueChange = { perDay = it },
            label = { Text("Per Day Salary") },
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        // --- Save & Continue ---
        Button(
            onClick = {
                if (monthly.isNotBlank() && perDay.isNotBlank()) {
                    onSaveAndContinue(monthly, perDay)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save & Continue", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // --- Continue without change ---
        OutlinedButton(
            onClick = onContinueWithoutChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue with existing salary", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
