package com.krishan.furrypal.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.krishan.furrypal.ui.Screen

@Composable
fun DogsListView(dogLists: List<String>, navController: NavHostController) {
    LazyColumn {
        itemsIndexed(items = dogLists) { _, it ->
            DogItem(item = it, navController = navController)
        }
    }
}

@Composable
fun DogItem(item: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate(Screen.Details(breedName = item))
            }),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(item, textAlign = TextAlign.Center, modifier = Modifier.padding(8.dp))
    }
}