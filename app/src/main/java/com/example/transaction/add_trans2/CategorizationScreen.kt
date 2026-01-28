package com.example.transaction.add_trans2

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import java.io.OutputStreamWriter

data class PriceRange(val label: String, val min: Int, val max: Int?)

val priceRanges = listOf(
    PriceRange("Below 100", 0, 99),
    PriceRange("200 - 1000", 200, 1000),
    PriceRange("1100 - 10000", 1100, 10000),
    PriceRange("11000 - 25000", 11000, 25000),
    PriceRange("25000 - 50000", 25000, 50000),
    PriceRange("51000 - 100000", 51000, 100000),
    PriceRange("100000+", 100000, null)
)

@Composable
fun CategorizationScreen(viewModel: CategorizationViewModel = viewModel()) {

    val context = LocalContext.current
    val items by viewModel.items.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    var selectedRange by remember { mutableStateOf<PriceRange?>(null) }
    var filterExpanded by remember { mutableStateOf(false) }

    // ----------- CSV DOWNLOAD LAUNCHER -----------
    val createFileLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("text/csv")) { uri: Uri? ->
            uri?.let {
                try {
                    context.contentResolver.openOutputStream(it)?.let { stream ->
                        val writer = OutputStreamWriter(stream)
                        writer.append("Item,Price\n")
                        items.forEach { item ->
                            writer.append("${item.name},${item.price}\n")
                        }
                        writer.flush()
                        writer.close()
                        Toast.makeText(context, "Downloaded successfully", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    LaunchedEffect(Unit) { viewModel.loadItemsFromFirebase() }

    // ----------- FILTERED LIST (SAFE DERIVED STATE) -----------
    val filteredItems = items.filter { item ->
        val price = item.price.toIntOrNull() ?: 0

        val matchesSearch =
            item.name.contains(searchQuery, ignoreCase = true)

        val matchesRange =
            selectedRange?.let {
                price >= it.min && (it.max == null || price <= it.max)
            } ?: true

        matchesSearch && matchesRange
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // -------- HEADER --------
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

        // -------- COLUMN LABELS --------
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

        // -------- SEARCH BAR --------
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Search item…") },
            singleLine = true
        )

        // -------- FILTER + DOWNLOAD ROW --------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box {
                Button(onClick = { filterExpanded = true }) {
                    Text(selectedRange?.label ?: "Filter by Price")
                }

                DropdownMenu(
                    expanded = filterExpanded,
                    onDismissRequest = { filterExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Clear Filter") },
                        onClick = {
                            selectedRange = null
                            filterExpanded = false
                        }
                    )

                    priceRanges.forEach { range ->
                        DropdownMenuItem(
                            text = { Text(range.label) },
                            onClick = {
                                selectedRange = range
                                filterExpanded = false
                            }
                        )
                    }
                }
            }

            Button(onClick = {
                createFileLauncher.launch("transactions.csv")
            }) {
                Text("Download")
            }
        }

        // -------- ERROR MESSAGE --------
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        // -------- ITEM LIST --------
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFFADFF2F))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(filteredItems) { index, item ->
                ExpenseItemRow(index, item, viewModel)
            }
        }

        // -------- SAVE BUTTON --------
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

// -------- ITEM ROW (UNCHANGED) --------
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
