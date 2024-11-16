package com.hussein.composecodelab.compose.register

// ViewState
data class RegisterViewState(
    val username: String = "",
    val usernameError: String = "", // Error message for username
    val email: String = "",
    val emailError: String = "",    // Error message for email
    val password: String = "",
    val passwordError: String = "", // Error message for password
    val confirmPassword: String = "",
    val confirmPasswordError: String = "", //Error message for confirm password
    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)