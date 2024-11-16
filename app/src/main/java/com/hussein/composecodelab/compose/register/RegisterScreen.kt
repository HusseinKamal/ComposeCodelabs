package com.hussein.composecodelab.compose.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun RegisterScreen(viewModel: RegisterViewModel = viewModel(), onRegisterSuccess: () -> Unit) {

    val viewState by viewModel.viewState.collectAsState()
    var username by remember { mutableStateOf(viewState.username) }
    var email by remember { mutableStateOf(viewState.email) }
    var password by remember { mutableStateOf(viewState.password) }
    var confirmPassword by remember { mutableStateOf(viewState.confirmPassword) }


    if (viewState.registrationSuccess){
        onRegisterSuccess()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomOutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                viewModel.onEvent(RegisterViewEvent.UsernameChanged(it))
            },
            placeholder = "Username",
            errorMessage = viewState.usernameError,
        )
        Spacer(modifier = Modifier.height(8.dp))




        CustomOutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                viewModel.onEvent(RegisterViewEvent.EmailChanged(it))
            },
            errorMessage = viewState.emailError,
            placeholder = "Email",
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomOutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.onEvent(RegisterViewEvent.PasswordChanged(it))
            },
            isPassword = true,
            placeholder = "Password",
            errorMessage = viewState.passwordError,
            )
        Spacer(modifier = Modifier.height(8.dp))

        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                viewModel.onEvent(RegisterViewEvent.ConfirmPasswordChanged(it))


            },
            isPassword = true,
            placeholder ="Confirm Password",
            errorMessage = viewState.confirmPasswordError,
            )

        Spacer(modifier = Modifier.height(16.dp))

        viewState.error?.let { errorMessage ->
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp)) // Add some spacing between the error message and the button
        }




        RoundedButtonWithProgress(text = "Register",isLoading = viewState.isLoading,onClick = {
            viewModel.onEvent(RegisterViewEvent.RegisterButtonClicked)
        })

    }
}