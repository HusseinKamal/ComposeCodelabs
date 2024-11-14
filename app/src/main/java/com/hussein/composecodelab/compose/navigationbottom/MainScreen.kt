package com.hussein.composecodelab.compose.navigationbottom

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hussein.composecodelab.codelab1.HomeScreen
import com.hussein.composecodelab.compose.AutoSizeText

@Composable
fun MainScreen(count: Int=0) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Profile,
        Screen.Cart // Add Cart screen
    )

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.navigationBarsPadding().windowInsetsPadding(
                WindowInsets.navigationBars)) { // Use NavigationBar in Material3

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(screen.icon, contentDescription = screen.title)
                            if (screen.route == Screen.Cart.route)
                                Badge(count = count) // Show badge for Cart screen
                            },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true

                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }

                            }

                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Your NavHost or content here with innerPadding
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Cart.route) { CartScreen() } // Add Cart screen navigation
        }
    }
}
@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Badge(count: Int) {

    if (count > 0) { // Only show badge if count > 0
        Surface(
            color = Color.Red,
            shape = CircleShape,
            modifier = Modifier.size(18.dp).absoluteOffset(x = 12.dp, y = (-4).dp)

        ) {
            AutoSizeText(
                text = count.toString(),
                textSize = 10.sp,
                colorText = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize(Alignment.Center)

            )

        }
    }
}