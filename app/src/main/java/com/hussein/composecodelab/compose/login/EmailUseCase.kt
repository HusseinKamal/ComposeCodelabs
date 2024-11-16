package com.hussein.composecodelab.compose.login

import android.util.Patterns

object EmailUseCase {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}