package com.example.apitest.RickandMortyAPI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun CharactersScreen(
    viewModel: CharactersViewModel
) {
    val items = viewModel.items.collectAsLazyPagingItems()

    when (val state = items.loadState.refresh) {
        is LoadState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Loading characters...")
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Text(text = "Error loading data: ${state.error.localizedMessage}")
        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                    items(items.itemCount) { index ->
                        val character = items[index]
                        if (character != null) {
                            CharacterCard(character = character)
                        }
                    }
                    // 下にスクロールして追加読み込み中のインジケーター表示
                    if (items.loadState.append is LoadState.Loading) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }

                        }
                    }

                    // 追加読み込みエラー時の表示
                    if (items.loadState.append is LoadState.Error) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            val error = (items.loadState.append as LoadState.Error).error
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "追加読み込みエラー: ${error.message}")
                            }
                        }
                    }
                }
            }

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
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
            )
        }
    }
}