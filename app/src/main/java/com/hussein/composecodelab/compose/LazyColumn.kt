package com.hussein.composecodelab.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun LazyColumnWithMultipleView(modifier: Modifier = Modifier,
                               items: List<RowType>) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { index, item -> // Use itemsIndexed for access to index if needed
            when (item) {
                is RowType.ImageRow -> {
                    // Your Image composable here
                  AsyncImage( model = item.imageUrl, contentDescription = null)
                }
                is RowType.TextRow -> {
                    Text(text = item.text) // Your Text composable here
                }
                is RowType.ButtonRow -> {
                    Button(onClick = item.onClick) {  // Your Button composable here
                        Text(text = item.text)
                    }
                }

                // ... handle other row types as needed
            }
        }
    }
}

// Sealed class to represent different view types
sealed class RowType {
    data class ImageRow(val imageUrl: String) : RowType()
    data class TextRow(val text: String) : RowType()
    data class ButtonRow(val text: String, val onClick: () -> Unit) : RowType()
    // Add more row types as needed...
}
val items = listOf(
    RowType.TextRow("This is a text row"),
    RowType.ImageRow("https://th.bing.com/th/id/OIG1.clHUuw_Z5tEPXWatEQhu"),
    RowType.ButtonRow(text = "Click Me") { /* Button click action */ },
    RowType.TextRow("Another text row"),
    RowType.ImageRow("https://plus.unsplash.com/premium_photo-1673306778968-5aab577a7365?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YmFja2dyb3VuZCUyMGltYWdlfGVufDB8fDB8fHww"),
    // Add more items of different types...
)

@Composable
fun CustomLazyColumn(
    modifier: Modifier = Modifier,
    items: List<Any>, // Generic item type
    itemContent: @Composable (index: Int, item: Any) -> Unit // Item content lambda
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            itemContent(index, item)
        }
    }
}


/*
@Composable
fun <T : Any> PagingLazyColumn(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (index: Int, item: T?) -> Unit
) {

    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            itemContent(index, item)
        }

        items(items.loadState.append) { loadState ->
            if (loadState is LoadState.Loading) { // Display loading indicator when appending
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }


            if (loadState is LoadState.Error){
                item {
                    Text(text = "Error")
                }
            }

        }
    }
}*/

@Composable
fun SwipeRefreshLazyColumnDemo() {
    var items by remember { mutableStateOf(List(20) { "Item $it" }) }
    var refreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = refreshing)


    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000) // Simulate network request
            items = List(20) { "New Item $it" } // Update the list with new data
            refreshing = false
        }
    }

    Box(Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { refreshing = true },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true, // Adjust as needed
                    backgroundColor = MaterialTheme.colorScheme.primary, // Customize colors
                    contentColor = Color.White
                )
            }

        ) {


            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

            }

        }

        // Show loading indicator while refreshing (optional)
        if (refreshing) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
//Best practices --------------------------------Start
@Composable
fun BestPracticeSwipeRefresh(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeRefreshState = remember { SwipeRefreshState(isRefreshing = refreshing) }

    // Use a Box to overlay loading indicator (optional)
    Box(Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true, // Let's make it scale!
                    backgroundColor = MaterialTheme.colorScheme.primary, // Customize colors
                    contentColor = Color.White
                )
            }
        ) {
            content()  // Your LazyColumn or other content goes here
        }

        // Optional loading indicator (can be customized)
        if (refreshing) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
@Composable
fun MyScreen() {
    var items by remember { mutableStateOf(List(20) { "Item $it" }) }
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000) // Simulate refresh (replace with your logic)
            items = List(20) { "New Item $it" }
            refreshing = false
        }
    }


    BestPracticeSwipeRefresh(
        refreshing = refreshing,
        onRefresh = { refreshing = true }
    ) {  // Content lambda for what goes inside SwipeRefresh
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
//----------------------------------End


