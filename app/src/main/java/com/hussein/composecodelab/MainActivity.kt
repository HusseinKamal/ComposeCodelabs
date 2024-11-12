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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.hussein.composecodelab.compose.AutoSizeText
import com.hussein.composecodelab.compose.CircleImage
import com.hussein.composecodelab.compose.CustomLazyHorizontalGrid
import com.hussein.composecodelab.compose.CustomLazyVerticalGrid
import com.hussein.composecodelab.compose.CustomOutlinedTextField
import com.hussein.composecodelab.compose.RoundedButtonWithProgress
import com.hussein.composecodelab.compose.TextField
import com.hussein.composecodelab.compose.data
import com.hussein.composecodelab.ui.theme.ComposeCodeLabTheme
import kotlinx.coroutines.delay
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
                /*//Email Validation with Value class
                Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
                    UserScreen(viewModel)
                }*/

                //AutoSizeText
                //AutoSizeText(text = "Asser Hussein", modifier = Modifier.fillMaxWidth().padding(20.dp), textSize = 20.sp, colorText = Color.Red)
                //Edit Text
                var text by remember { mutableStateOf("") }
               /* TextField(
                    modifier = Modifier.padding(top = 50.dp).fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it },
                    label = "",
                    placeholder = "Enter your email",
                    keyboardActions= KeyboardActions.Default)*/
               /* CustomOutlinedTextField(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it },
                    placeholder = "Enter your email",
                    keyboardActions= KeyboardActions.Default
                )*/

                //Circle Image
                /*CircleImage(imageUrl = "https://img.freepik.com/premium-photo/gentle-ripples-calm-ocean-sunset_251136-107438.jpg",modifier = Modifier.padding(50.dp))*/

                //Lazy Grid
                /*CustomLazyVerticalGrid(
                    modifier = Modifier.fillMaxSize().padding(30.dp),
                    data = data,
                    cells =2  // Set a fixed number of columns
                ) { index, imageUrl ->

                    CircleImage(
                        imageUrl = imageUrl.toString(), // Pass the imageUrl to the CircularImageWithBorder
                        modifier = Modifier.padding(8.dp), // Add padding around each image
                        borderColor = Color.Gray,
                        borderWidth = 3f,
                    )

                }

                CustomLazyHorizontalGrid(
                    modifier = Modifier.height(300.dp).padding(top =30.dp),
                    data = data,
                    cells = 1  // Set a fixed number of columns
                ) { index, imageUrl ->

                    CircleImage(
                        imageUrl = imageUrl.toString(), // Pass the imageUrl to the CircularImageWithBorder
                        modifier = Modifier.padding(8.dp), // Add padding around each image
                        borderColor = Color.Gray,
                        borderWidth = 3f,
                    )

                }*/

                //Image with loader
                var isLoading by remember { mutableStateOf(false) }

                RoundedButtonWithProgress(modifier=Modifier.padding(top = 50.dp).fillMaxWidth(),onClick = {
                    isLoading = true
                    // Delay for demonstration
                    lifecycleScope.launch {
                        delay(3000)  // Simulate some loading time
                        isLoading = false
                    }


                }, text = "Click Me", isLoading = isLoading)

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