package com.example.transaction.view_exp

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewExpenseViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _totalExpense = MutableStateFlow(0)
    val totalExpense: StateFlow<Int> = _totalExpense

    private val _itemNames = MutableStateFlow<List<String>>(emptyList())
    val itemNames: StateFlow<List<String>> = _itemNames

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadExpenses() {
        val user = auth.currentUser ?: return

        firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { document ->

                val expenses =
                    document.get("expenses") as? Map<String, Map<String, String>>
                        ?: emptyMap()

                var total = 0
                val names = mutableListOf<String>()

                expenses.values.forEach { item ->
                    val name = item["name"] ?: ""
                    val price = item["price"]?.toIntOrNull() ?: 0

                    if (name.isNotBlank()) {
                        names.add(name)
                        total += price
                    }
                }

                _totalExpense.value = total
                _itemNames.value = names
            }
            .addOnFailureListener {
                _errorMessage.value = "Failed to load expenses"
            }
    }
}
