package com.example.transaction.mainMenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R
import com.example.transaction.add_trans1.AddTrans1Activity
import com.example.transaction.graphHistory.GraphActivity
import com.example.transaction.salaryInput.SalaryInputActivity
import com.example.transaction.view_exp.ViewExpenseActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnGraph = findViewById<Button>(R.id.btnGraph)
        val btnTutorial = findViewById<Button>(R.id.btnTutorial)

        // 1️⃣ Add Transaction → SalaryInputActivity first
        btnAdd.setOnClickListener {
            val intent = Intent(this, SalaryInputActivity::class.java)
            startActivity(intent)
        }

        // 2️⃣ View Expenses → ViewExpenseActivity
        btnView.setOnClickListener {
            val intent = Intent(this, ViewExpenseActivity::class.java)
            startActivity(intent)
        }

        // 3️⃣ Graph & History → GraphActivity
        btnGraph.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }

        // 4️⃣ Tutorial → TODO
        btnTutorial.setOnClickListener {
            // TODO: Add tutorial navigation
        }
    }
}
