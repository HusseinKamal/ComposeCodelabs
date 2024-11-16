package com.hussein.composecodelab.compose.login

// ViewEvent
sealed class LoginViewEvent {
    data class EmailChanged(val email: String) : LoginViewEvent()
    data class PasswordChanged(val password: String) : LoginViewEvent()
    object LoginClicked : LoginViewEvent()
}