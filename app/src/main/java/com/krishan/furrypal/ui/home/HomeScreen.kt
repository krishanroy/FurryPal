package com.krishan.furrypal.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.krishan.furrypal.domain.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = viewModel()
    val dogsResponse = homeViewModel.homeUiStateFlow.collectAsState().value

    if (dogsResponse.discoverFurryExpert.furryBreedNames?.isNotEmpty() == true) {
        DogsListView(dogsResponse.discoverFurryExpert.furryBreedNames, navController = navController)
    } else if (dogsResponse.discoverFurryExpert.isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else if (dogsResponse.discoverFurryExpert.isError) {
        Toast.makeText(LocalContext.current, "Something went wrong!", Toast.LENGTH_SHORT).show()
    }
}

