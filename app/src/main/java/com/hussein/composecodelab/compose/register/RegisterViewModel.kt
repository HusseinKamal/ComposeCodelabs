package com.hussein.composecodelab.compose.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.composecodelab.compose.login.EmailUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(RegisterViewState())
    val viewState: StateFlow<RegisterViewState> = _viewState

    private val _viewEvents = MutableSharedFlow<RegisterViewEvent>()

    init {
        viewModelScope.launch {
            _viewEvents.collect { event ->
                when (event) {
                    is RegisterViewEvent.UsernameChanged -> reduce(RegisterPartialState.Username(event.username))
                    is RegisterViewEvent.EmailChanged -> reduce(RegisterPartialState.Email(event.email))
                    is RegisterViewEvent.PasswordChanged -> reduce(RegisterPartialState.Password(event.password))
                    is RegisterViewEvent.ConfirmPasswordChanged -> reduce(RegisterPartialState.ConfirmPassword(event.confirmPassword))
                    is RegisterViewEvent.RegisterButtonClicked -> performRegistration()
                }
            }
        }
    }


    private suspend fun reduce(partialState: RegisterPartialState) {
        val newState = when (partialState) {
            is RegisterPartialState.Username -> _viewState.value.copy(username = partialState.username)
            is RegisterPartialState.Email -> _viewState.value.copy(email = partialState.email)
            is RegisterPartialState.Password -> _viewState.value.copy(password = partialState.password)
            is RegisterPartialState.ConfirmPassword -> _viewState.value.copy(confirmPassword = partialState.confirmPassword)
            is RegisterPartialState.Loading -> _viewState.value.copy(isLoading = partialState.isLoading)
            is RegisterPartialState.Error -> _viewState.value.copy(error = partialState.error,emailError ="", usernameError = "", passwordError = "", confirmPasswordError = "")
            is RegisterPartialState.RegisterSuccess ->  _viewState.value.copy(registrationSuccess = partialState.registerSuccess,error =null)
            is RegisterPartialState.ErrorEmail -> viewState.value.copy(emailError = partialState.error, usernameError = "", passwordError = "", confirmPasswordError = "",error =null)
            is RegisterPartialState.ErrorUserName -> viewState.value.copy(usernameError = partialState.error,emailError ="", passwordError = "", confirmPasswordError = "",error =null)
            is RegisterPartialState.ErrorPassword -> viewState.value.copy(passwordError = partialState.error,emailError ="", usernameError = "", confirmPasswordError = "",error =null)
            is RegisterPartialState.ErrorConfirmPassword -> viewState.value.copy(confirmPasswordError = partialState.error,emailError ="", passwordError = "", usernameError = "",error =null)
        }
        _viewState.emit(newState)


    }

    fun onEvent(event: RegisterViewEvent) {
        viewModelScope.launch { _viewEvents.emit(event) }
    }


    private fun performRegistration() {
        viewModelScope.launch {
            val state = viewState.value

            // 1. Input Validation
            if (state.username.isBlank() || state.email.isBlank() || state.password.isBlank() || state.confirmPassword.isBlank()) {
                reduce(RegisterPartialState.Error("Please fill in all fields"))
                reduce(RegisterPartialState.Loading(false)) // Ensure loading state is reset
                return@launch
            }

            if(!EmailUseCase.isValidEmail(state.email)){
                reduce(RegisterPartialState.ErrorEmail("Invalid email"))
                reduce(RegisterPartialState.Loading(false))
                return@launch
            }
            if (state.password != state.confirmPassword) {
                reduce(RegisterPartialState.ErrorPassword("Passwords do not match"))
                reduce(RegisterPartialState.Loading(false))
                return@launch
            }

            // 2. Set Loading State
            reduce(RegisterPartialState.Loading(true))
            reduce(RegisterPartialState.Error("")) // Clear any previous errors


            // 3. Perform Registration (Simulate for now)
            delay(2000) // Simulate network delay

            val registrationSuccessful = true // Replace with your actual registration logic

            // 4. Handle Result
            if (registrationSuccessful) {
                reduce(RegisterPartialState.RegisterSuccess(true))
                reduce(RegisterPartialState.Loading(false))
            } else {
                reduce(RegisterPartialState.Error("Registration failed. Please try again."))
                reduce(RegisterPartialState.Loading(false))

            }
        }
    }

}