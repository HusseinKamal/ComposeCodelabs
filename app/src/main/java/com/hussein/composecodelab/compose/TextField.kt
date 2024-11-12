package com.hussein.composecodelab.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.hussein.composecodelab.R

@Composable
fun TextField(modifier: Modifier = Modifier,
              value: String,
              onValueChange: (String) -> Unit,
              label: String = "",
              placeholder: String = "",
              leadingIcon: @Composable (() -> Unit)? = null,
              trailingIcon: @Composable (() -> Unit)? = null,
              isError: Boolean = false,
              keyboardType: KeyboardType = KeyboardType.Text,
              imeAction: ImeAction = ImeAction.Done,
              keyboardActions: KeyboardActions = KeyboardActions.Default,
              isPassword: Boolean = false) {
    var passwordVisible by remember { mutableStateOf(false) }


    Column(modifier = modifier) {
        if (label.isNotEmpty()) { // Only show the label if it's provided
            Text(
                text = label,
                style =TextStyle(fontSize = 14.sp), // Customize label style
                modifier = Modifier.padding(bottom = 4.dp) // Add spacing between label and text field
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (!isError) Color.LightGray.copy(alpha = 0.3f) else MaterialTheme.colorScheme.error.copy(
                        alpha = 0.3f
                    )
                )
                .border(
                    width = 1.dp,
                    color = if (!isError) Color.LightGray else MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),  // Inner padding


            textStyle = TextStyle(fontSize = 16.sp, fontFamily = fontFamilyName), // Input text size
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation = if (isPassword) {
                if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },


            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween  // Align items in a row
                )
                {
                    if (leadingIcon != null) {
                        leadingIcon()
                        Spacer(modifier = Modifier.width(8.dp)) // Add space after leading icon
                    }

                    Box(Modifier.weight(1f)) { // Use Box to center the placeholder
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder, style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword) {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter =painterResource(id = if (passwordVisible) R.drawable.ic_lock_close else R.drawable.ic_lock_open),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }


                    if (trailingIcon != null) {

                        trailingIcon()
                    }
                }
            }

        )

        if (isError) { // Show error message if needed
            Text(
                text = "Error message here", // Provide actual error message
                color = MaterialTheme.colorScheme.error,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun CustomOutlinedTextField(modifier: Modifier = Modifier,
                            value: String = "",
                            onValueChange: (String) -> Unit,
                            placeholder: String = "",
                            leadingIcon: @Composable (() -> Unit)? = null,
                            trailingIcon: @Composable (() -> Unit)? = null,
                            shape: Shape = OutlinedTextFieldDefaults.shape,
                            isError: Boolean = false,
                            keyboardType: KeyboardType = KeyboardType.Text,
                            imeAction: ImeAction = ImeAction.Done,
                            keyboardActions: KeyboardActions = KeyboardActions.Default,
                            isPassword: Boolean = false) {
    val passwordVisible by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(TextFieldValue(value)) } // Use TextFieldValue

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue: TextFieldValue -> // Must take TextFieldValue
            textValue = newValue // Update the state
        },
        isError = isError,
        shape = shape,
        modifier = modifier,
        placeholder = { Text(text = placeholder) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions, // Only one keyboardActions parameter
        textStyle = TextStyle(fontSize = 16.sp, fontFamily = fontFamilyName), // Input text size
        visualTransformation = if (isPassword) {
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
    )
}
