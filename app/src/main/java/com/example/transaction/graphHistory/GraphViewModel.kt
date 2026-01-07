package com.example.transaction.graphHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Event wrapper for one-time events
class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /** Returns the content if it hasn't been handled yet */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /** Returns the content, even if it's already been handled */
    fun peekContent(): T = content
}

class GraphViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<Event<String>>()
    val navigationEvent: LiveData<Event<String>> = _navigationEvent

    fun onBarGraphClicked() {
        _navigationEvent.value = Event("BAR")
    }

    fun onLineGraphClicked() {
        _navigationEvent.value = Event("LINE")
    }

    fun onHistoryClicked() {
        _navigationEvent.value = Event("HISTORY")
    }
}
