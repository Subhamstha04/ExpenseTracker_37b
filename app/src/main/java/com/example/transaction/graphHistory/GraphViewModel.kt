package com.example.transaction.graphHistory

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GraphHistoryViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _expenses = MutableStateFlow<List<GraphExpense>>(emptyList())
    val expenses: StateFlow<List<GraphExpense>> = _expenses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadExpenses() {
        val user = auth.currentUser ?: return

        firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { doc ->

                val expenseMap =
                    doc.get("expenses") as? Map<String, Map<String, String>>

                val list = expenseMap?.values?.mapNotNull {
                    val name = it["name"]
                    val price = it["price"]?.toIntOrNull()

                    if (name != null && price != null) {
                        GraphExpense(name, price)
                    } else null
                } ?: emptyList()

                _expenses.value = list
            }
            .addOnFailureListener {
                _error.value = "Failed to load expenses"
            }
    }
}
