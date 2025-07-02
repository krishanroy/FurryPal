package com.krishan.furrypal.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.krishan.furrypal.Screen

@Composable
fun DetailScreen(navController: NavHostController) {
    val breedName = navController.currentBackStackEntry?.toRoute<Screen.Details>()?.breedName.toString()
    val viewModel: DetailsViewModel = viewModel()
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Surface(
                shape = RoundedCornerShape(12.dp), color = Color.LightGray,
                modifier = Modifier
                    .padding(16.dp)
                    .size(75.dp)
            ) {
                //AsyncImage()
            }
            Text(
                breedName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(
                    onClick = {
                        navController.popBackStack()
                    }
                )
            )
        }
    }
}