package com.example.transaction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.transaction.add_trans1.AddTrans1Activity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Safely start AddTrans1Activity
        startActivity(Intent(this, AddTrans1Activity::class.java))

        // Finish MainActivity if you don't want it in back stack
        finish()
    }
}
