package com.hussein.composecodelab.compose.navigationbottom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SearchScreen() {
    Text("Search Screen", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
}
