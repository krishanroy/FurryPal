package com.krishan.furrypal.ui.home.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.krishan.furrypal.ui.common.LoadingIndicator
import com.krishan.furrypal.ui.home.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val dogsResponse = homeViewModel.homeUiStateFlow.collectAsState().value

    if (dogsResponse.discoverFurryExpert.furryBreedNames?.isNotEmpty() == true) {
        DogsListView(dogsResponse.discoverFurryExpert.furryBreedNames, navController = navController)
    } else if (dogsResponse.discoverFurryExpert.isLoading) {
        LoadingIndicator()
    } else if (dogsResponse.discoverFurryExpert.isError) {
        Toast.makeText(LocalContext.current, "Something went wrong!", Toast.LENGTH_SHORT).show()
    }
}

