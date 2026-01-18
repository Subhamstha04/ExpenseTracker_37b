package com.example.transaction

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    private var listText = ""
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val backBtn = findViewById<ImageButton>(R.id.btnBack)
        val etItem = findViewById<EditText>(R.id.etItem)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val listArea = findViewById<TextView>(R.id.listArea)

        backBtn.setOnClickListener { finish() }

        btnAdd.setOnClickListener {
            val item = etItem.text.toString().trim()
            val price = etPrice.text.toString().trim()

            if (item.isNotEmpty() && price.isNotEmpty()) {
                listText += "$count. $item - Rs $price\n"
                listArea.text = listText
                count++
                etItem.text.clear()
                etPrice.text.clear()
            }
        }
    }
}
