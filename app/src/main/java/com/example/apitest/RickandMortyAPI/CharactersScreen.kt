package com.example.apitest.RickandMortyAPI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel
) {
    val items = viewModel.items.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(items.value.size) { index ->
            CharacterCard(character = items.value[index])
        }
    }
}

@Composable
fun CharacterCard(
    character: Character
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(160.dp, 100.dp)
    ) {
        Row {
            Column {
                Text(text = character.name)
                Text(text = "Status: ${character.status}")
                Text(text = "Species: ${character.species}")
            }
            AsyncImage(
                model = character.image,
                contentDescription = "Character Image",
            )
        }
    }
}