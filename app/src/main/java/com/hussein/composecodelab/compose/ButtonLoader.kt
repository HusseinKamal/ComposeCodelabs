package com.hussein.composecodelab.compose


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RoundedButtonWithProgress(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    isLoading: Boolean = false,
    cornerRadius: Int = 20,
    backgroundColor: Color = Color.Blue,
    textColor: Color = Color.White,
    progressColor: Color = Color.White
) {
    Button(
        onClick = { if (enabled && !isLoading) onClick() },
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(contentColor = backgroundColor), // Set background color
        contentPadding = PaddingValues(12.dp) // Add padding

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = progressColor,
                    strokeWidth = 2.dp
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(text = text, color = textColor)
        }
    }
}