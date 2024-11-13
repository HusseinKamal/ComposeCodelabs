package com.hussein.composecodelab.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hussein.composecodelab.R
import java.util.Locale

@Composable
fun LocalizedScreen() {
    val context = LocalContext.current
    var locale by remember { mutableStateOf(context.resources.configuration.locales[0]) } // Get current locale


    val greeting = stringResource(R.string.edit_message) // Access localized string
    val message = stringResource(R.string.bottom_navigation_profile)


    Column(modifier = Modifier.padding(top = 20.dp)) {
        Text(text = message)



        // Buttons to change the language
        Button(onClick = { locale = Locale("en") }) {  // English
            Text("English")
        }
        Button(onClick = { locale = Locale("ar") }) {  // Spanish
            Text("Arabic")
        }

        // Apply the locale to the CompositionLocalProvider
        CompositionLocalProvider(LocalConfiguration provides Configuration().apply {
            setLocale(locale)  // Set the new locale
        }) {
            // Content that should be recomposed with the updated locale
            // (Any composables inside this block will use the updated locale)
            LocalizedComposable()
        }
    }
}





@Composable
fun LocalizedComposable(){
    val greeting = stringResource(id = R.string.edit_message)
    Text(text = greeting)

}