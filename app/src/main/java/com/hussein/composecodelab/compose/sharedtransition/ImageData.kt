package com.hussein.composecodelab.compose.sharedtransition

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hussein.composecodelab.R

data class ImageData(val id: Int, val imageResource: Int, val title: String)

@Composable
fun ImageListScreen(navController: NavHostController) {
    val imageList = listOf(  // Sample image data
        ImageData(1, R.drawable.img, "Image 1"), // Replace with your actual drawables and titles
        ImageData(2, R.drawable.img_1, "Image 2"),
        ImageData(3, R.drawable.ic_launcher_background, "Image 3")
    )

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        items(imageList, key = { it.id }) { imageData ->
            ImageItem(imageData, navController)
        }
    }
}




@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ImageItem(imageData: ImageData, navController: NavHostController) {


    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.clickable {

        navController.navigate("details/${imageData.id}")
    }) {


        Image(
            painter = painterResource(id = imageData.imageResource),
            contentDescription = null, // Provide a description
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop

        )

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()

        ) {
            Text(imageData.title)
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailsScreen(imageId: Int, navController: NavController) {
    val imageList = listOf(
        ImageData(1, R.drawable.img, "Image 1"),
        ImageData(2, R.drawable.img_1, "Image 2"),
        ImageData(3, R.drawable.ic_launcher_background, "Image 3")
    )



    val imageData = imageList.find { it.id == imageId }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Image Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            imageData?.let {
                Image(
                    painter = painterResource(id = it.imageResource),

                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}



@Composable
fun SharedTransitionElement(navController: NavHostController = rememberNavController()){



    NavHost(navController = navController, startDestination = "list",
        ){

        composable("list"){
            ImageListScreen(navController)
        }

        composable(
            "details/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.IntType }),



            enterTransition = {

                slideInHorizontally(
                    initialOffsetX = {300},
                    animationSpec = tween(700)


                ) + fadeIn(animationSpec = tween(700))


            },

            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {300},
                    animationSpec = tween(700)
                ) + fadeOut(
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = {-300}, // Slide in from the left
                    animationSpec = tween(700)
                )
            },

            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {-300}, // Slide out to the left
                    animationSpec = tween(700)
                )
            }






        ) { backStackEntry ->

            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
            ImageDetailsScreen(imageId, navController)


        }




    }



}