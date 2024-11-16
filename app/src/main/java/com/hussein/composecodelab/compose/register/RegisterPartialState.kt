package com.hussein.composecodelab.compose.register

sealed class RegisterPartialState {
    data class Username(val username: String) : RegisterPartialState()
    data class Email(val email: String) : RegisterPartialState()
    data class Password(val password: String) : RegisterPartialState()
    data class ConfirmPassword(val confirmPassword: String) : RegisterPartialState()

    data class Loading(val isLoading: Boolean) : RegisterPartialState()
    data class Error(val error: String="") : RegisterPartialState()
    data class ErrorUserName(val error: String="") : RegisterPartialState()
    data class ErrorEmail(val error: String="") : RegisterPartialState()
    data class ErrorPassword(val error: String="") : RegisterPartialState()
    data class ErrorConfirmPassword(val error: String="") : RegisterPartialState()

    data class RegisterSuccess(val registerSuccess: Boolean) : RegisterPartialState()
}
