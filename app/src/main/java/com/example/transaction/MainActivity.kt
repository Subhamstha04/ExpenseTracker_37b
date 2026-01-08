package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.transaction.add_trans2.CategorizationActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Safely start CategorizationActivity
        startActivity(Intent(this, CategorizationActivity::class.java))

        // Finish MainActivity if you don't want it in back stack
        finish()
    }
}
