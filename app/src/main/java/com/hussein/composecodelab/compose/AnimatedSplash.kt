package com.hussein.composecodelab.compose

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hussein.composecodelab.R
import com.hussein.composecodelab.compose.navigationbottom.MainScreen
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(3000L) // Adjust delay as needed

        navController.popBackStack() //remove splash screen to avoid going back to splash screen.
        navController.navigate("main_screen") // Navigate to your main screen
    }

    // Splash screen content
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img), // Replace with your image
            contentDescription = "Logo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.scale(scale.value).fillMaxSize()
        )
    }
}

@Composable
fun NavigationSplash() {
    val navController = rememberNavController()
    NavHost(navController = navController, modifier = Modifier.fillMaxSize(), startDestination = "splash_screen") {
        composable("splash_screen") { AnimatedSplashScreen(navController) }
        composable("main_screen") { MainScreen() } // Replace MainScreen() with your actual main screen composable
        // ... other composable routes
    }
}