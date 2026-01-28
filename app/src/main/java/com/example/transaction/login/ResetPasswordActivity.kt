package com.example.transaction.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var email by remember { mutableStateOf("") }
            var oldPassword by remember { mutableStateOf("") }
            var newPassword by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }

            val auth = FirebaseAuth.getInstance()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Reset Password", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = { oldPassword = it },
                    label = { Text("Old Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm New Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                        if (
                            email.isBlank() ||
                            oldPassword.isBlank() ||
                            newPassword.length < 6 ||
                            newPassword != confirmPassword
                        ) {
                            Toast.makeText(
                                this@ResetPasswordActivity,
                                "Please check all fields",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val user = auth.currentUser
                        if (user == null) {
                            Toast.makeText(
                                this@ResetPasswordActivity,
                                "User not logged in",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val credential =
                            EmailAuthProvider.getCredential(email, oldPassword)

                        // 🔐 STEP 1: Re-authenticate
                        user.reauthenticate(credential)
                            .addOnSuccessListener {

                                // 🔐 STEP 2: Update password
                                user.updatePassword(newPassword)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            this@ResetPasswordActivity,
                                            "Password updated successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        finish()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            this@ResetPasswordActivity,
                                            it.localizedMessage
                                                ?: "Password update failed",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this@ResetPasswordActivity,
                                    "Re-authentication failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update Password")
                }
            }
        }
    }
}
