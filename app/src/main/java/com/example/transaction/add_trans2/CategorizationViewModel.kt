package com.example.transaction.add_trans2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// --- Data class for an expense item ---
data class ExpenseItem(
    val name: String = "",
    val price: String = ""
)

class CategorizationViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // List of 12 editable items
    private val _items = MutableStateFlow(List(12) { ExpenseItem() })
    val items: StateFlow<List<ExpenseItem>> = _items

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Update a specific item in the list
    fun updateItem(index: Int, name: String, price: String) {
        _items.value = _items.value.toMutableList().also {
            it[index] = ExpenseItem(name.trim(), price.trim())
        }
    }

    // Save items to Firebase (public now)
    fun saveItemsToFirebase(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            onError("User not logged in")
            return
        }

        viewModelScope.launch {
            val userId = user.uid
            val itemsMap = _items.value.mapIndexed { index, item ->
                "item$index" to mapOf("name" to item.name, "price" to item.price)
            }.toMap()

            firestore.collection("users")
                .document(userId)
                .set(mapOf("expenses" to itemsMap))
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e -> onError(e.message ?: "Unknown error") }
        }
    }

    // Load items from Firebase when the screen opens
    fun loadItemsFromFirebase() {
        val user = auth.currentUser ?: return
        firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { document ->
                val expenses = document.get("expenses") as? Map<String, Map<String, String>>
                if (expenses != null) {
                    val loadedItems = List(12) { i ->
                        val data = expenses["item$i"]
                        ExpenseItem(
                            name = data?.get("name") ?: "",
                            price = data?.get("price") ?: ""
                        )
                    }
                    _items.value = loadedItems
                }
            }
            .addOnFailureListener {
                _errorMessage.value = "Failed to load items"
            }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
