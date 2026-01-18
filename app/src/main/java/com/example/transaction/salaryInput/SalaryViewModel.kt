package com.example.transaction.salaryInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SalaryViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // --- StateFlows for UI ---
    private val _monthly = MutableStateFlow("")
    val monthly: StateFlow<String> = _monthly

    private val _perDay = MutableStateFlow("")
    val perDay: StateFlow<String> = _perDay

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        // Load existing salary from Firebase on ViewModel creation
        loadSalaryFromFirebase()
    }

    fun onMonthlyChange(value: String) {
        _monthly.value = value
    }

    fun onPerDayChange(value: String) {
        _perDay.value = value
    }

    /** Save salary data to Firestore */
    fun saveSalary(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            onError("User not logged in")
            return
        }

        val data = mapOf(
            "monthly" to _monthly.value.trim(),
            "perDay" to _perDay.value.trim()
        )

        viewModelScope.launch {
            firestore.collection("users")
                .document(user.uid)
                .set(data)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e -> onError(e.message ?: "Unknown error") }
        }
    }

    /** Load salary from Firestore for persistence */
    fun loadSalaryFromFirebase() {
        val user = auth.currentUser ?: return
        firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                _monthly.value = doc.getString("monthly") ?: ""
                _perDay.value = doc.getString("perDay") ?: ""
            }
            .addOnFailureListener { e ->
                _errorMessage.value = e.message ?: "Failed to load salary"
            }
    }
}
