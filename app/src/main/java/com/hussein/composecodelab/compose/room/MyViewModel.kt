package com.hussein.composecodelab.compose.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyViewModel(private val userDao: UserDao) : ViewModel() {
    // ... use the userDao here
    private val _users = MutableStateFlow<List<User>>(emptyList())  // Private mutable state
    val users: StateFlow<List<User>> = _users                      // Public immutable state

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading



    init {
        getAllUsers()  // Fetch users when the ViewModel is initialized
    }


    fun getAllUsers() {
        viewModelScope.launch {
            _isLoading.value = true // Show Loading UI
            userDao.getAllUsers()
                .catch { exception ->
                    _errorMessage.value = exception.message // Show error message (Optional)
                    // Handle exceptions (e.g., log, show error message)
                }
                .collectLatest { users ->  // Collect the latest data from the flow
                    _users.value = users
                    _isLoading.value = false // Hide Loading UI

                }

        }

    }



    fun insertUser(user: User) {
        viewModelScope.launch {
            try {
                userDao.insert(user)
            }catch (e:Exception){
                _errorMessage.value = e.message
            }

        }
    }
}