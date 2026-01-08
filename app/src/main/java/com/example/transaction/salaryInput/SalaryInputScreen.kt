package com.example.transaction.salaryInput

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transaction.R
import androidx.compose.ui.draw.clip

@Composable
fun SalaryInputScreen(
    onContinueClicked: (monthly: Double, perDay: Double) -> Unit
) {
    var monthlyInput by remember { mutableStateOf("") }
    var perDayInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // IMAGE AT THE TOP
        Image(
            painter = painterResource(id = R.drawable.bucket_pic),
            contentDescription = "Salary Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Monthly salary
        Text(
            text = "Enter Monthly Salary",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = monthlyInput,
            onValueChange = { monthlyInput = it },
            label = { Text("Monthly Salary") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Per day salary
        Text(
            text = "Enter Per Day Salary",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = perDayInput,
            onValueChange = { perDayInput = it },
            label = { Text("Per Day Salary") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Continue button
        Button(
            onClick = {
                val monthly = monthlyInput.toDoubleOrNull()
                val perDay = perDayInput.toDoubleOrNull()
                if (monthly != null && perDay != null) {
                    onContinueClicked(monthly, perDay)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CONTINUE")
        }
    }
}
