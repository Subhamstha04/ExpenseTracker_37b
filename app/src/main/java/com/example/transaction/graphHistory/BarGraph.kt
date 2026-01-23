package com.example.transaction.graphHistory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun BarGraph(
    expenses: List<GraphExpense>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context)
        },
        update = { chart ->
            val entries = expenses.mapIndexed { index, expense ->
                BarEntry(index.toFloat(), expense.amount.toFloat())
            }

            val dataSet = BarDataSet(entries, "Expenses")
            val data = BarData(dataSet)

            chart.data = data
            chart.description.isEnabled = false
            chart.invalidate()
        }
    )
}
