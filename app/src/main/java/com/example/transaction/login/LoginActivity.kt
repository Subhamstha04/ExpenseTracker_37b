package com.example.transaction.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.transaction.mainMenu.MainMenuActivity
import com.example.transaction.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class LoginActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            LoginScreen(
                onLoginClicked = { email, password ->
                    if (email.isNotBlank() && password.isNotBlank()) {

                        // 🔐 Firebase login
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    val user = auth.currentUser
                                    if (user != null) {

                                        // ✅ VERY IMPORTANT: merge = true (DOES NOT DELETE salary)
                                        firestore.collection("users")
                                            .document(user.uid)
                                            .set(
                                                mapOf(
                                                    "email" to user.email,
                                                    "uid" to user.uid
                                                ),
                                                SetOptions.merge()
                                            )
                                    }

                                    Toast.makeText(
                                        this,
                                        "Login successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    startActivity(
                                        Intent(this, MainMenuActivity::class.java)
                                    )
                                    finish()

                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception?.localizedMessage ?: "Login failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                    } else {
                        Toast.makeText(
                            this,
                            "Please fill both fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onSignUpClicked = {
                    startActivity(
                        Intent(this, RegisterActivity::class.java)
                    )
                }
            )
        }
    }
}
