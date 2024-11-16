package com.hussein.composecodelab.compose.login

// Partial State Updates
sealed class LoginPartialState {
    data class Email(val email: String) : LoginPartialState()
    data class Password(val password: String) : LoginPartialState()
    data class Loading(val isLoading: Boolean) : LoginPartialState()
    data class Error(val error: String?) : LoginPartialState()
    data class LoginSuccess(val loginSuccess: Boolean) :
        LoginPartialState() // Add login success partial state

}