package com.hussein.composecodelab.compose.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hussein.composecodelab.compose.CustomOutlinedTextField
import com.hussein.composecodelab.compose.RoundedButtonWithProgress

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), onLoginSuccess: () -> Unit) {

    val viewState by viewModel.viewState.collectAsState()

    var email by remember { mutableStateOf(viewState.email) }
    var password by remember { mutableStateOf(viewState.password) }


    // Check for successful login and trigger callback
    if (viewState.loginSuccess) {
        onLoginSuccess()
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CustomOutlinedTextField(
            value = email,
            placeholder = "Email",
            onValueChange = {
                email = it
                viewModel.onEvent(LoginViewEvent.EmailChanged(it))

            },
        )

        Spacer(modifier = Modifier.height(8.dp))


        CustomOutlinedTextField(
            value = password,
            placeholder = "Password",
            onValueChange = {
                password = it
                viewModel.onEvent(LoginViewEvent.PasswordChanged(it))

            },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))


        viewState.error?.let { errorMessage ->
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }




        RoundedButtonWithProgress(
            onClick = {
                viewModel.onEvent(LoginViewEvent.LoginClicked)
                      },
            enabled = !viewState.isLoading, // Disable button while loading
            text = "Login",
            isLoading = viewState.isLoading
        )


    }
}