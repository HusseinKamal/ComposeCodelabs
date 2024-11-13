package com.hussein.composecodelab.compose.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun ScreenUser(viewModel: MyViewModel = koinViewModel()) {  // Inject the ViewModel

    val users by viewModel.users.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()



    Column(modifier = Modifier.padding(30.dp).fillMaxSize()) {

        Button(modifier = Modifier.padding(10.dp), onClick = {
            val newUser = User(id = Random.nextInt(0, Int.MAX_VALUE), name = "New User")
            viewModel.insertUser(newUser)
        }) {
            Text("Add User")
        }

        if (isLoading) {
            CircularProgressIndicator() // Or your preferred loading indicator
        } else {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(users) { user ->
                    Text(user.name)
                }
            }
        }

        errorMessage?.let {
            Text("Error: $it", color = Color.Red) // Display the error message
        }

    }
}