package com.hussein.composecodelab.codelab2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.composecodelab.R
import com.hussein.composecodelab.ui.theme.Purple40
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun ClickableMessage(message:MessageData)
{
    var showMessage by remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        ClickableText(modifier = Modifier.background(Color.Gray),text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue, fontSize = 20.sp)) {message.body}},

            onClick = {
                showMessage = !showMessage
            })
        AnimatedVisibility(visible = showMessage) {
            Text(text = message.author)
        }
    }

}

data class MessageData(val author:String,val body:String,val time:String)

/*@Composable
fun AnimatedVContent(modifier: Modifier = Modifier) {
    AnimatedContent(
        modifier = modifier,
        targetState = 1,
        transitionSpec = {
            slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            ).with(
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Down
                )
            )
        }
    ) {targetState ->
        
    }
}*/
@Composable
fun SurveyTopProgress(index:Int,totalQuestionsCount:Int){
    val progress by animateFloatAsState(targetValue =(index+1)/totalQuestionsCount.toFloat())
    LinearProgressIndicator(progress = { progress })
}


@Composable
fun PulsatingCircle() {
    // Create an infinite transition
    val infiniteTransition = rememberInfiniteTransition()

    // Animate the scale of the circle
    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Draw the animated circle
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawPulsatingCircle(animatedScale)
    }
}

private fun DrawScope.drawPulsatingCircle(scale: Float) {
    val radius = size.minDimension / 4 * scale // Adjust radius based on scale
    drawCircle(
        color = Color.Blue,
        radius = radius,
        center = center // Draw at the center of the canvas
    )
}

@Composable
fun animatedImageCircle() {
    // Create an infinite transition
    val infiniteTransition = rememberInfiniteTransition()

    // Animate the scale of the circle
    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

//    val bitmap: Bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.ic_launcher_foreground)

    // Convert the Bitmap to an ImageBitmap
    //val imageBitmap = bitmap.asImageBitmap()

    // Display the bitmap using Image composable
    // Draw the animated circle
    Image(painter = painterResource(id = R.drawable.img),contentDescription = "Logo",modifier = Modifier
        .drawBehind {
            rotate(animatedScale.absoluteValue) {
                drawCircle(Purple40, style = Stroke(10f))
            }
        }
        .padding(16.dp)
        .clip(CircleShape)
        .fillMaxSize())
}

@Composable
fun AnimateVisibleCompose(modifier: Modifier = Modifier) {
    var shown by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier.fillMaxWidth(), // Fill the entire available space
            contentAlignment = Alignment.Center // Center the content
        ) {
            Button(onClick = { shown = !shown }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Show Message")
            }
        }
        AnimatedVisibility(
            modifier = modifier.padding(16.dp).swipeToDismiss {

            }.clip(RoundedCornerShape(corner = CornerSize(20.dp))),
            visible = shown,
            enter = slideInVertically(
                // Enters by sliding down from offset -fullHeight to 0.
                initialOffsetY = { fullHeight -> -fullHeight }
            ),
            exit = slideOutVertically(
                // Exits by sliding up from offset 0 to -fullHeight.
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                color = MaterialTheme.colorScheme.secondary,
                shadowElevation = 4.dp
            ) {
                Text(
                    text = stringResource(R.string.edit_message),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

private fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    // This Animatable stores the horizontal offset for the element.
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                // Interrupt any ongoing animation.
                offsetX.stop()
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        // Record the position after offset
                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        launch {
                            // Overwrite the Animatable value while the element is dragged.
                            offsetX.snapTo(horizontalDragOffset)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocity = velocityTracker.calculateVelocity().x
                // Calculate where the element eventually settles after the fling animation.
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                // The animation should end as soon as it reaches these bounds.
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        // Not enough velocity; Slide back to the default position.
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        // Enough velocity to slide away the element to the edge.
                        offsetX.animateDecay(velocity, decay)
                        // The element was swiped away.
                        onDismissed()
                    }
                }
            }
        }
    }
        // Apply the horizontal offset to the element.
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}