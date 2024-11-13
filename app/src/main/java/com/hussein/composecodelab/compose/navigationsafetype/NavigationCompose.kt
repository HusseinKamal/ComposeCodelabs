package com.hussein.composecodelab.compose.navigationsafetype

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun NavigationSafeType(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = DogListRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<DogListRoute> {
                DogListScreen(
                    onDogClick = { dog, breedSize ->
                        navController.navigate(
                            DogDetailRoute(
                                dog = dog,
                                breedSize = breedSize
                            )
                        )
                    }
                )
            }
            composable<DogDetailRoute>(
                typeMap = mapOf(
                    typeOf<Dog>() to CustomNavType.DogType,
                    typeOf<BreedSize>() to NavType.EnumType(BreedSize::class.java)
                )
            ) {
                val arguments = it.toRoute<DogDetailRoute>()
                DogDetailScreen(
                    dog = arguments.dog,
                    breedSize = arguments.breedSize
                )
            }
        }
    }

}

@Serializable
data object DogListRoute

@Serializable
data class DogDetailRoute(
    val dog: Dog,
    val breedSize: BreedSize
)
