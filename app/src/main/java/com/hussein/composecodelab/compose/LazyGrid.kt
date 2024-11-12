package com.hussein.composecodelab.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomLazyVerticalGrid(
    modifier: Modifier = Modifier,
    data: List<Any>, // Make data generic
    cells: Int = 2, // Customizable number of columns/cells
    itemContent: @Composable (index: Int, item: Any) -> Unit // Generic item content lambda
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(data) { index, item -> // Use itemsIndexed to access both index and item
            itemContent(index, item)
        }
    }
}

@Composable
fun CustomLazyHorizontalGrid(
    modifier: Modifier = Modifier,
    data: List<Any>, // Make data generic
    cells: Int = 2, // Customizable number of columns/cells
    itemContent: @Composable (index: Int, item: Any) -> Unit // Generic item content lambda
) {
    LazyHorizontalGrid(
        modifier = modifier,
        rows = GridCells.Fixed(cells) // Set the number of rows/cells
    ) {
        itemsIndexed(data) { index, item -> // Use itemsIndexed to access both index and item
            itemContent(index, item)
        }
    }
}
val data = listOf("https://plus.unsplash.com/premium_photo-1673306778968-5aab577a7365?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YmFja2dyb3VuZCUyMGltYWdlfGVufDB8fDB8fHww",
    "https://th.bing.com/th/id/OIG1.clHUuw_Z5tEPXWatEQhu",
    "https://images.ctfassets.net/hrltx12pl8hq/28ECAQiPJZ78hxatLTa7Ts/2f695d869736ae3b0de3e56ceaca3958/free-nature-images.jpg?fit=fill&w=1200&h=630",
    "https://fps.cdnpk.net/images/home/subhome-ai.webp?w=649&h=649",
    "https://i0.wp.com/picjumbo.com/wp-content/uploads/amazing-stone-path-in-forest-free-image.jpg?w=600&quality=80")