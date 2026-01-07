package com.example.transaction.graphHistory

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels        // Important import!
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.databinding.ActivityGraphBinding

class GraphActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraphBinding
    private val viewModel: GraphViewModel by viewModels() // Correct ViewModel delegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button click listeners
        binding.btnBar.setOnClickListener {
            viewModel.onBarGraphClicked()
        }

        binding.btnLine.setOnClickListener {
            viewModel.onLineGraphClicked()
        }

        binding.btnHistory.setOnClickListener {
            viewModel.onHistoryClicked()
        }

        // Observe navigation events
        viewModel.navigationEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let { value ->
                // Show Toast
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()

                // Example navigation (replace with actual activities)
                /*
                when (value) {
                    "BAR" -> startActivity(Intent(this, BarGraphActivity::class.java))
                    "LINE" -> startActivity(Intent(this, LineGraphActivity::class.java))
                    "HISTORY" -> startActivity(Intent(this, HistoryActivity::class.java))
                }
                */
            }
        }
    }
}
