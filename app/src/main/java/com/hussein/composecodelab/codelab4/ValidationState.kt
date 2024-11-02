package com.hussein.composecodelab.codelab4

sealed class ValidationState {
    object Idle : ValidationState()
    object Valid : ValidationState()
    data class Invalid(val error: String) : ValidationState()
}