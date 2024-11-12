package com.hussein.composecodelab.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hussein.composecodelab.R
import kotlin.random.Random

@Composable
fun CircleImage(
    imageUrl: String,
    borderColor: Color = Color.Blue,
    borderWidth: Float = 4f,
    size: Int = 100,
    placeholder:Int = R.drawable.ic_launcher_background,
    error:Int = R.drawable.ic_launcher_background,
    contentDescription: String = generateRandomString(),
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        placeholder = painterResource(placeholder),
        error = painterResource(error),
        contentDescription = contentDescription, // Provide a content description
        modifier = modifier.size(size.dp)
            .clip(CircleShape)
            .border(width = borderWidth.dp, color = borderColor, shape = CircleShape),
        contentScale = contentScale
    )
}

fun generateRandomString(length: Int = 10, allowedChars: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"): String {
    return buildString {
        repeat(length) {
            append(allowedChars[Random.nextInt(allowedChars.length)])
        }
    }
}