package com.hussein.composecodelab.codelab4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(viewModel: UserViewModel = UserViewModel()) {
    val state by viewModel.state.collectAsState()

    var emailInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("Email") },
            isError = state is ValidationState.Invalid
        )

        Button(onClick = {
            val email = Email(emailInput) // This will validate the email
            viewModel.handleIntent(UserIntent.ValidateEmail(email))
        }) {
            Text("Validate")
        }

        when (state) {
            is ValidationState.Idle -> {
                // Initial state, do nothing
            }
            is ValidationState.Valid -> {
                Text("Email is valid!", color = Color.Green)
            }
            is ValidationState.Invalid -> {
                Text("Error: ${(state as ValidationState.Invalid).error}", color = Color.Red)
            }
        }
    }
}