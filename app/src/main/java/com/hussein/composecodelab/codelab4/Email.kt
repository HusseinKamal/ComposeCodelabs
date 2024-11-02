package com.hussein.composecodelab.codelab4

@JvmInline
value class Email (val email: String) {
    init {
        require(email.isNotEmpty() &&
                email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        ) {
            "Invalid email format"
        }
    }
}