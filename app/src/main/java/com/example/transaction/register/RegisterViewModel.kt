package com.example.transaction.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // --- State Flows for UI binding ---
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // --- UI interaction functions ---
    fun onEmailChange(newEmail: String) = run { _email.value = newEmail }
    fun onPasswordChange(newPassword: String) = run { _password.value = newPassword }
    fun onConfirmPasswordChange(newConfirm: String) = run { _confirmPassword.value = newConfirm }
    fun clearError() = run { _errorMessage.value = null }

    // --- Email/Password Registration ---
    fun registerUser(onSuccess: () -> Unit) {
        val emailVal = _email.value.trim()
        val passwordVal = _password.value.trim()
        val confirmVal = _confirmPassword.value.trim()

        if (emailVal.isBlank() || passwordVal.isBlank() || confirmVal.isBlank()) {
            _errorMessage.value = "All fields are required"
            return
        }

        if (passwordVal != confirmVal) {
            _errorMessage.value = "Passwords do not match"
            return
        }

        _loading.value = true
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(emailVal, passwordVal)
                .addOnCompleteListener { task ->
                    _loading.value = false
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        _errorMessage.value = task.exception?.localizedMessage
                    }
                }
        }
    }

    // --- Google Sign-In ---
    fun loginWithGoogle(idToken: String, onSuccess: () -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        _loading.value = true
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    _errorMessage.value = task.exception?.localizedMessage
                }
            }
    }
}
