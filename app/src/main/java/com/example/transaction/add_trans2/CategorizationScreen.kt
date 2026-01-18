package com.example.transaction.add_trans2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun CategorizationScreen(viewModel: CategorizationViewModel = viewModel()) {
    val context = LocalContext.current
    val items by viewModel.items.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val scope = rememberCoroutineScope()

    // Load items once when screen appears
    LaunchedEffect(Unit) { viewModel.loadItemsFromFirebase() }

    Column(modifier = Modifier.fillMaxSize()) {

        // --- Header ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF008000))
                .border(2.dp, Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CATEGORIZATION",
                color = Color.White,
                fontSize = 28.sp
            )
        }

        // --- Column Labels ---
        Row(modifier = Modifier.height(40.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Text("Item", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFF800080)),
                contentAlignment = Alignment.Center
            ) {
                Text("Price", color = Color.White)
            }
        }

        // --- Error message ---
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        // --- Items List ---
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFFADFF2F))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(items) { index, item ->
                ExpenseItemRow(index, item, viewModel)
            }
        }

        // --- Save Button ---
        Button(
            onClick = {
                scope.launch {
                    viewModel.saveItemsToFirebase(
                        onSuccess = {
                            Toast.makeText(context, "Items saved!", Toast.LENGTH_SHORT).show()
                        },
                        onError = { msg ->
                            Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
        ) {
            Text("Save Items")
        }
    }
}

@Composable
fun ExpenseItemRow(index: Int, item: ExpenseItem, viewModel: CategorizationViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.2f))
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${index + 1}.", modifier = Modifier.width(24.dp))

        TextField(
            value = item.name,
            onValueChange = { viewModel.updateItem(index, it, item.price) },
            singleLine = true,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(4.dp))

        TextField(
            value = item.price,
            onValueChange = { viewModel.updateItem(index, item.name, it) },
            singleLine = true,
            modifier = Modifier.weight(0.4f)
        )
    }
}
