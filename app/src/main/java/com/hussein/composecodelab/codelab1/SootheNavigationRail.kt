package com.hussein.composecodelab.codelab1

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hussein.composecodelab.R

@Composable
fun SootheNavigationRail(modifier: Modifier = Modifier) {
   NavigationRail(
   ) {
       Column(
       ) {
           NavigationRailItem(
               icon = {
                   Icon(
                       imageVector = Icons.Default.Star,
                       contentDescription = null
                   )
               },
               label = {
                   Text(stringResource(R.string.bottom_navigation_home))
               },
               selected = true,
               onClick = {}
           )

           NavigationRailItem(
               icon = {
                   Icon(
                       imageVector = Icons.Default.AccountCircle,
                       contentDescription = null
                   )
               },
               label = {
                   Text(stringResource(R.string.bottom_navigation_profile))
               },
               selected = false,
               onClick = {}
           )
       }
   }
}