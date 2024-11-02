package com.hussein.composecodelab.codelab4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class UserViewModel : ViewModel() {
    private val _state = MutableStateFlow<ValidationState>(ValidationState.Idle)
    val state: StateFlow<ValidationState> get() = _state

    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.ValidateEmail -> validateEmail(intent.email)
        }
    }

    private fun validateEmail(email: Email) {
        _state.value = try {
            // If the email is valid, update the state to Valid
            ValidationState.Valid
        } catch (e: Exception) {
            // If validation fails, update the state with the error message
            ValidationState.Invalid(e.message ?: "Unknown error")
        }
    }
}