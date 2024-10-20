package com.hussein.composecodelab.codelab1

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hussein.composecodelab.DrawableStringPair
import com.hussein.composecodelab.R

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    val favoriteCollectionsData = listOf(
        R.drawable.img to R.string.ab1_inversions,
        R.drawable.img_1 to R.string.ab1_inversions,
        R.drawable.img to R.string.ab1_inversions,
        R.drawable.img_1 to R.string.ab1_inversions,
        R.drawable.img to R.string.ab1_inversions,
        R.drawable.img_1 to R.string.ab1_inversions
    ).map { DrawableStringPair(it.first, it.second) }
    LazyHorizontalGrid(//Horizontal recyclerview with multiple rows
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}