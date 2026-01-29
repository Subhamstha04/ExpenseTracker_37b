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
import com.example.transaction.tutorial.TutorialActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnGraph = findViewById<Button>(R.id.btnGraph)
        val btnTutorial = findViewById<Button>(R.id.btnTutorial)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, SalaryInputActivity::class.java))
        }

        btnView.setOnClickListener {
            startActivity(Intent(this, ViewExpenseActivity::class.java))
        }

        btnGraph.setOnClickListener {
            startActivity(Intent(this, GraphActivity::class.java))
        }

        // ✅ Tutorial button WORKS now
        btnTutorial.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}
