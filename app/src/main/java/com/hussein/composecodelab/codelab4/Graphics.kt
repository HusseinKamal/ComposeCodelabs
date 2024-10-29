package com.hussein.composecodelab.codelab4

import android.icu.util.ValueIterator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hussein.composecodelab.R

@Composable
fun CustomLayout(content: @Composable () -> Unit
                 ,modifier: Modifier = Modifier) {

    Layout(content = { /*TODO*/ }, measurePolicy = {measure, constraints ->


        //Add custom layout here width and height of the layout
        val pacrcable=measure.map {
            it.measure(constraints.copy(
                minWidth = constraints.minWidth * 10,
                minHeight = constraints.minWidth * 10
            ))
        }

        /*layout(pacrcable.sumOf { it.width },pacrcable.sumOf { it.height}){

        }*/
        layout(500,500){
            val xposition=0
            val yposition=0
            pacrcable.map { it.place(xposition,yposition) }
        }

        //You can overrcide methods here
    })
}

@Composable
fun LayoutModifierExample(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(40.dp)
        .background(Color.LightGray), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(color = Color.LightGray, thickness = 8.dp, modifier = Modifier.padding(10.dp))
        Divider(color = Color.LightGray, thickness = 8.dp)
        Divider(color = Color.LightGray, thickness = 8.dp)
        Divider(color = Color.LightGray, thickness = 8.dp)

    }
}

@Composable
fun ImageCompose(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null,
        modifier = Modifier
            .padding(20.dp)
            .size(100.dp)
            .clip(CircleShape) //This should be end as compose execute sequential
    )
}