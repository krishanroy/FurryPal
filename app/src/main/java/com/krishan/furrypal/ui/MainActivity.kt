package com.krishan.furrypal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krishan.furrypal.R
import com.krishan.furrypal.ui.details.DetailScreen
import com.krishan.furrypal.ui.home.components.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            Scaffold(
                modifier = Modifier.Companion.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ), title = {
                            Text(stringResource(R.string.furry_home_page))
                        }, navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    )
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier.Companion.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NavHost(navController = navController, startDestination = Screen.Home) {
                        composable<Screen.Home> {
                            HomeScreen(navController = navController)
                        }
                        composable<Screen.Details> {
                            DetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


sealed class Screen() {
    @Serializable
    object Home : Screen()

    @Serializable
    data class Details(val breedName: String) : Screen()
}