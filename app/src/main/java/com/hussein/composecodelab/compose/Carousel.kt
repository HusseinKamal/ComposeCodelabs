package com.hussein.composecodelab.compose
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue


@Composable
fun Carousel(
    items: List<String>, // Replace String with your data type
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 8.dp,
    scaleMultiplier: Float = 0.4f // Adjust scaling effect
) {
    val itemWidth = 200.dp  // Adjust card width as needed
    val carouselState = remember { CarouselState() }

    LazyRow(
        modifier = modifier.padding(top = 10.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    // Calculate carousel offset based on drag amount.  Snap to nearest item
                    val newOffset = carouselState.offset + dragAmount
                    val snapOffset = calculateSnapOffset(newOffset, itemWidth, items.size, itemSpacing)
                    carouselState.offset = snapOffset

                    change.consume()
                }
            },

        contentPadding = PaddingValues(horizontal = 32.dp), // Add horizontal padding

        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {

        itemsIndexed(items) { index, item ->


            val scale = animateFloatAsState(
                targetValue = calculateScale(carouselState.offset, index, itemWidth, items.size, itemSpacing, scaleMultiplier),
                animationSpec = tween(300), label = ""

            ).value

            Card(
                modifier = Modifier
                    .width(itemWidth)
                    .graphicsLayer {
                        // Calculate the current item's offset from the center

                        val itemOffset = (index * (itemWidth.value + itemSpacing.value)) + carouselState.offset
                        val centerOffset = itemOffset
                        this.translationX = centerOffset
                        this.scaleX = scale
                        this.scaleY = scale

                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)


            ) {
                // Your item content here.
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item)
                }
            }
        }

    }

}

class CarouselState {
    var offset by mutableFloatStateOf(0f)
}




private fun calculateScale(
    offset: Float,
    index: Int,
    itemWidth: Dp,
    itemCount: Int,
    itemSpacing: Dp,
    scaleMultiplier: Float
): Float {
    val itemCenter = (index * (itemWidth.value + itemSpacing.value)) - (itemWidth.value / 2)
    val distanceFromCenter = (offset - itemCenter).absoluteValue

    val scale = 1f - (distanceFromCenter / (itemWidth.value + itemSpacing.value)) * scaleMultiplier
    return scale.coerceIn(0.8f, 1f) // Restrict scaling within a range

}





private fun calculateSnapOffset(
    currentOffset: Float,
    itemWidth: Dp,
    itemCount: Int,
    itemSpacing: Dp
): Float {
    val snapOffsets = mutableListOf<Float>()
    for (i in 0 until itemCount) {
        snapOffsets.add(i * (itemWidth.value + itemSpacing.value))

    }

    val closestSnapOffset = snapOffsets.minByOrNull { (it - currentOffset).absoluteValue }


    return closestSnapOffset ?: 0f

}



// Sample usage:
@Composable
fun CarouselDemo() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5") }

    Carousel(
        items = items,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)

    )


}

