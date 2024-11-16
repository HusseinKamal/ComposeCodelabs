package com.hussein.composecodelab.compose.register

// ViewEvent
sealed class RegisterViewEvent {
    data class UsernameChanged(val username: String) : RegisterViewEvent()
    data class EmailChanged(val email: String) : RegisterViewEvent()
    data class PasswordChanged(val password: String) : RegisterViewEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterViewEvent()
    object RegisterButtonClicked : RegisterViewEvent()
}