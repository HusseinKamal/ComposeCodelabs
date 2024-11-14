package com.hussein.composecodelab.compose.navigationbottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Search : Screen("search", "Search", Icons.Filled.Search)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    object Cart : Screen("cart", "Cart", Icons.Filled.ShoppingCart) // Add Cart screen data class

}