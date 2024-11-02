package com.hussein.composecodelab

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hussein.composecodelab.codelab2.ClickableMessage
import com.hussein.composecodelab.codelab2.MessageData
import com.hussein.composecodelab.codelab3.ImageCompose
import com.hussein.composecodelab.codelab4.Email
import com.hussein.composecodelab.codelab4.UserIntent
import com.hussein.composecodelab.codelab4.UserScreen
import com.hussein.composecodelab.codelab4.UserViewModel
import com.hussein.composecodelab.codelab4.ValidationState
import com.hussein.composecodelab.ui.theme.ComposeCodeLabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCodeLabTheme {

                //Codelab1
             /* Scaffold(
                    bottomBar = { SootheBottomNavigation() }
                ) { padding ->
                    HomeScreen(Modifier.padding(padding))
                }*/

                //Codelab2
                //Text(text = "Hello" , color = Color(0xFFE91E63))

                //Codelab3
                //ClickableMessage(MessageData(author = "Hussein", body = "Hello Compose", time = "12:00"))
                //SurveyTopProgress(5,10)
                //PulsatingCircle()
                //animatedImageCircle()
                //AnimateVisibleCompose()

                //Codelab4
                //LayoutModifierExample()
                //ImageCompose()
              /*  lifecycleScope.launch {
                    viewModel.state.collect { state ->
                        when (state) {
                            is ValidationState.Idle -> {
                                // Show initial state
                            }
                            is ValidationState.Valid -> {
                                // Show success message
                            }
                            is ValidationState.Invalid -> {
                                // Show error message
                                Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }*/
                //Email Validation with Value class
                Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
                    UserScreen(viewModel)
                }
               
            }
        }
    }
    private fun onValidateButtonClick(emailInput: String) {
        val email = Email(emailInput) // This will validate the email
        viewModel.handleIntent(UserIntent.ValidateEmail(email))
    }
}
data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)