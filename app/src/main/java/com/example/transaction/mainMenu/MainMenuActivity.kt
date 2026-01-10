package com.example.transaction.mainMenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.transaction.R
import com.example.transaction.add_trans1.AddTrans1Activity
import com.example.transaction.view_exp.ViewExpenseActivity
import com.example.transaction.graphHistory.GraphActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnGraph = findViewById<Button>(R.id.btnGraph)
        val btnTutorial = findViewById<Button>(R.id.btnTutorial)

        // 1️⃣ Add Transaction → AddTrans1Activity
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddTrans1Activity::class.java))
        }

        // 2️⃣ View Expenses → ViewExpenseActivity
        btnView.setOnClickListener {
            startActivity(Intent(this, ViewExpenseActivity::class.java))
        }

        // 3️⃣ Graph & History → GraphActivity
        btnGraph.setOnClickListener {
            startActivity(Intent(this, GraphActivity::class.java))
        }

        // 4️⃣ Tutorial → TODO: implement later
        btnTutorial.setOnClickListener {
            // TODO: Navigate to tutorial screen
        }
    }
}
