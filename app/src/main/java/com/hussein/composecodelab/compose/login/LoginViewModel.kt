package com.hussein.composecodelab.compose.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState

    private val _viewEvents = MutableSharedFlow<LoginViewEvent>()


    fun onEvent(event: LoginViewEvent) {
        viewModelScope.launch{
            _viewEvents.emit(event)
        }
    }

    init {
        viewModelScope.launch {
            _viewEvents.collect { event ->
                when (event) {
                    is LoginViewEvent.EmailChanged -> reduce(LoginPartialState.Email(event.email))
                    is LoginViewEvent.PasswordChanged -> reduce(LoginPartialState.Password(event.password))
                    is LoginViewEvent.LoginClicked -> performLogin()
                }
            }


        }


    }


    private suspend fun reduce(partialState: LoginPartialState) {
        val newState = when (partialState) {
            is LoginPartialState.Email -> _viewState.value.copy(email = partialState.email)
            is LoginPartialState.Password -> _viewState.value.copy(password = partialState.password)
            is LoginPartialState.Loading -> _viewState.value.copy(isLoading = partialState.isLoading)
            is LoginPartialState.Error -> _viewState.value.copy(error = partialState.error)
            is LoginPartialState.LoginSuccess -> _viewState.value.copy(loginSuccess = partialState.loginSuccess)
        }

        _viewState.emit(newState)
    }

    private fun performLogin() {
        viewModelScope.launch {
            val state = viewState.value


            if (state.email.isBlank() || state.password.isBlank()) {
                reduce(LoginPartialState.Error("Email and password cannot be empty"))
                reduce(LoginPartialState.Loading(false))
                return@launch
            }

            reduce(LoginPartialState.Loading(true))
            reduce(LoginPartialState.Error(null))


            delay(2000)

            val loginSuccessful = state.email == "test@example.com" && state.password == "password"  // Replace with your actual login logic


            if (loginSuccessful) {
                reduce(LoginPartialState.LoginSuccess(true))
                reduce(LoginPartialState.Loading(false))



            } else {
                reduce(LoginPartialState.Error("Invalid email or password"))
                reduce(LoginPartialState.Loading(false))


            }

        }
    }

}