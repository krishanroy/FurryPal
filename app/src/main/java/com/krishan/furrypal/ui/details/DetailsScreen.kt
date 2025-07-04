package com.krishan.furrypal.ui.details

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.krishan.furrypal.ui.Screen
import com.krishan.furrypal.ui.common.LoadingIndicator

@Composable
fun DetailScreen(navController: NavHostController) {
    val breedName = navController.currentBackStackEntry?.toRoute<Screen.Details>()?.breedName.toString()
    val viewModel: DetailsViewModel = hiltViewModel()
    viewModel.handleIntent(intent = DetailsScreenIntent.FetchDogImage(breedName))
    val imageUrlStateFlow = viewModel.dogImageStateFlow.collectAsState().value
    BackHandler {
        // Perform custom actions
        // Then, optionally call popBackStack to go back
        navController.popBackStack()
    }

    when (imageUrlStateFlow) {
        is DetailsStateUi.Loading -> {
            LoadingIndicator()
        }

        is DetailsStateUi.Error -> {
            Toast.makeText(LocalContext.current, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }

        is DetailsStateUi.Success -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Column(verticalArrangement = Arrangement.Center) {
                    Surface(
                        shape = RoundedCornerShape(12.dp), color = Color.LightGray,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(275.dp)
                    ) {
                        AsyncImage(
                            model = imageUrlStateFlow.imageUrl,
                            contentDescription = "test image"
                        )
                    }
                    Text(
                        breedName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        is DetailsStateUi.NoImagesForThatBreed -> {
            Toast.makeText(LocalContext.current, "No image available for that breed", Toast.LENGTH_SHORT).show()
        }
    }
}