package com.example.transaction.salaryInput

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.transaction.add_trans1.AddTrans1Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SalaryInputActivity : ComponentActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SalaryInputScreen(

                // Option 1: Save & Continue
                onSaveAndContinue = { monthly, perDay ->
                    val user = auth.currentUser ?: return@SalaryInputScreen

                    firestore.collection("users")
                        .document(user.uid)
                        .set(
                            mapOf(
                                "monthly" to monthly,
                                "perDay" to perDay
                            ),
                            com.google.firebase.firestore.SetOptions.merge()
                        )
                        .addOnSuccessListener {
                            startActivity(
                                Intent(this, AddTrans1Activity::class.java)
                            )
                            finish()
                        }
                },

                // Option 2: Continue without change
                onContinueWithoutChange = {
                    startActivity(
                        Intent(this, AddTrans1Activity::class.java)
                    )
                    finish()
                }
            )
        }
    }
}
