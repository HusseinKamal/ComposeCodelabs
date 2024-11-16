package com.hussein.composecodelab.compose.login

// ViewState
data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false // Add loginSuccess state
)