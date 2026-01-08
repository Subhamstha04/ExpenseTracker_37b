package com.example.transaction.add_trans2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transaction.R
import androidx.compose.material3.TextFieldDefaults

@Composable
fun CategorizationScreen(viewModel: CategorizationViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {

        // 1. Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF008000))
                .border(width = 4.dp, color = Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CATEGORIZATION",
                color = Color.White,
            fontSize = 29.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // 2. Product Showcase
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pic4),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }

        // 3. Column Labels
        Row(modifier = Modifier.height(40.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Text("Items", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFF800080)),
                contentAlignment = Alignment.Center
            ) {
                Text("PRICE", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        // 4. Data List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFADFF2F))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(viewModel.items) { index, item ->
                ExpenseItemRow(index, item, viewModel)
            }
        }
    }
}

@Composable
fun ExpenseItemRow(index: Int, item: ExpenseItem, viewModel: CategorizationViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.2f))
            .padding(vertical = 2.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Number column
        Text("${index + 1}.", modifier = Modifier.width(24.dp))

        // Item column (long)
        TextField(
            value = item.name.ifBlank { "" },
            onValueChange = { viewModel.updateItem(index, it, item.price) },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            // colors parameter removed for compatibility
        )

        Spacer(modifier = Modifier.width(4.dp))

        // Price column (narrow)
        TextField(
            value = item.price.ifBlank { "" },
            onValueChange = { viewModel.updateItem(index, item.name, it) },
            singleLine = true,
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
            // colors parameter removed for compatibility
        )
    }
}
