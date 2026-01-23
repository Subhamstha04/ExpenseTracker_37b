package com.example.transaction.graphHistory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun LineGraph(
    expenses: List<GraphExpense>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context)
        },
        update = { chart ->
            val entries = expenses.mapIndexed { index, expense ->
                Entry(index.toFloat(), expense.amount.toFloat())
            }

            val dataSet = LineDataSet(entries, "Expense Trend")
            val data = LineData(dataSet)

            chart.data = data
            chart.description.isEnabled = false
            chart.invalidate()
        }
    )
}
