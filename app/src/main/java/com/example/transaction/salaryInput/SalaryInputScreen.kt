package com.example.transaction.salaryInput

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable

@Composable
fun SalaryInputScreen(
    onContinueClicked: (Double) -> Unit
) {
    var salaryInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enter Monthly Salary",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = salaryInput,
            onValueChange = { salaryInput = it },
            label = { Text("Monthly Salary") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val salary = salaryInput.toDoubleOrNull()
                if (salary != null) {
                    onContinueClicked(salary)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CONTINUE")
        }
    }
}
