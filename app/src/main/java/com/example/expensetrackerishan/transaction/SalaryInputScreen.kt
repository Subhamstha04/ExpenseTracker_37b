package com.example.expensetrackerishan.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetrackerishan.R

@Composable
fun SalaryInputScreen(
    onContinueClicked: (monthly: String, perDay: String) -> Unit
) {
    var monthlyInput by remember { mutableStateOf("") }
    var perDayInput by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔹 Top Image
            Image(
                painter = painterResource(id = R.drawable.pic2),
                contentDescription = "Salary Banner",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Title
            Text(
                text = "Enter Your Salary",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Monthly Salary Input
            OutlinedTextField(
                value = monthlyInput,
                onValueChange = { monthlyInput = it },
                label = { Text("Monthly Salary", fontSize = 18.sp) },
                textStyle = LocalTextStyle.current.copy(fontSize = 22.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Per Day Salary Input
            OutlinedTextField(
                value = perDayInput,
                onValueChange = { perDayInput = it },
                label = { Text("Per Day Salary", fontSize = 18.sp) },
                textStyle = LocalTextStyle.current.copy(fontSize = 22.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 🔹 Continue Button
            Button(
                onClick = {
                    if (monthlyInput.isNotBlank() && perDayInput.isNotBlank()) {
                        onContinueClicked(monthlyInput, perDayInput)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
