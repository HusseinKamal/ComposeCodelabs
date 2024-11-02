package com.hussein.composecodelab.codelab4

sealed class UserIntent {
    data class ValidateEmail(val email: Email) : UserIntent()
}