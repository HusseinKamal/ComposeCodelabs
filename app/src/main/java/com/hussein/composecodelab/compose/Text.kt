package com.hussein.composecodelab.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.hussein.composecodelab.R

@Composable
fun AutoSizeText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 12.sp,
    colorText: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = TextStyle.Default,
    textAlign :TextAlign = TextAlign.Unspecified,
    fontFamily: FontFamily = fontFamilyName,
) {
    val textStyleBody by remember { mutableStateOf(textStyle.copy(fontSize = textSize)) }

    Text(
        modifier = modifier,
        text = text,
        fontSize = textSize,
        color = colorText,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis, // Handle overflow with ellipsis or clip, etc.
        style = textStyleBody,
        textDecoration = textStyle.textDecoration,
        fontWeight = FontWeight.Normal,
        fontFamily =  fontFamily,
        textAlign = textAlign
    )
    
}
val fontFamilyName = FontFamily(
    Font(R.font.sanfransisco_regular, FontWeight.Light)
   /* Font(R.font.firasans_regular, FontWeight.Normal),
    Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.firasans_medium, FontWeight.Medium),
    Font(R.font.firasans_bold, FontWeight.Bold)*/
)