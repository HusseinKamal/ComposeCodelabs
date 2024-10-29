package com.hussein.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hussein.composecodelab.codelab1.HomeScreen
import com.hussein.composecodelab.codelab1.SootheBottomNavigation
import com.hussein.composecodelab.codelab2.AnimateVisibleCompose
import com.hussein.composecodelab.codelab2.ClickableMessage
import com.hussein.composecodelab.codelab2.MessageData
import com.hussein.composecodelab.codelab2.PulsatingCircle
import com.hussein.composecodelab.codelab2.SurveyTopProgress
import com.hussein.composecodelab.codelab2.animatedImageCircle
import com.hussein.composecodelab.codelab4.ImageCompose
import com.hussein.composecodelab.codelab4.LayoutModifierExample
import com.hussein.composecodelab.ui.theme.ComposeCodeLabTheme

class MainActivity : ComponentActivity() {
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
                ImageCompose()
            }
        }
    }
}
data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)