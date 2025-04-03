package com.example.apitest.DogAPI

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage

@Composable
fun DogImagesScreen(
    viewModel: DogViewModel
) {
    val dogImages = viewModel.items.collectAsLazyPagingItems()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(dogImages.itemCount) { index ->
            val dogImage = dogImages[index]
            DogCard(
                dogImage = dogImage!!,
                index = index
            )
        }
        dogImages.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        Text(text = "Error loading data")
                    }
                    Log.d("Paging", "Error loading data")
                }
            }
        }
    }
}

@Composable
fun DogCard(
    dogImage: String,
    index: Int
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dog No. $index",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )
            AsyncImage(
                model = dogImage,
                contentDescription = "Dog Image",
                modifier = Modifier
                    .width(120.dp)
            )
        }
    }
}