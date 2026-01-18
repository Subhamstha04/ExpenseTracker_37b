package com.example.transaction.add_trans1

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddTrans1ViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _monthlyIncome = MutableStateFlow("0")
    val monthlyIncome: StateFlow<String> = _monthlyIncome

    private val _dailyIncome = MutableStateFlow("0")
    val dailyIncome: StateFlow<String> = _dailyIncome

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        listenToSalary()
    }

    private fun listenToSalary() {
        val user = auth.currentUser ?: return
        val uid = user.uid

        firestore.collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    _errorMessage.value = error.message
                    Log.e("AddTrans1VM", "Firestore error", error)
                    return@addSnapshotListener
                }

                if (snapshot == null || !snapshot.exists()) {
                    Log.e("AddTrans1VM", "No salary document found")
                    return@addSnapshotListener
                }

                // 🔥 THIS is the important part
                val monthly = snapshot.get("monthly")
                val perDay = snapshot.get("perDay")

                Log.d("AddTrans1VM", "monthly=$monthly perDay=$perDay")

                _monthlyIncome.value = when (monthly) {
                    is String -> monthly
                    is Number -> monthly.toString()
                    else -> "0"
                }

                _dailyIncome.value = when (perDay) {
                    is String -> perDay
                    is Number -> perDay.toString()
                    else -> "0"
                }
            }
    }
}
