package com.example.apitest.JSONPlaceholder

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun PostsScreen(viewmodel: PostsViewModel) {
    val posts = viewmodel.items.collectAsLazyPagingItems()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(posts.itemCount) { index ->
            val post = posts[index]
            PostCard(
                post = post!!
            )
        }
        posts.apply {
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
fun PostCard(
    post: Post
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "No.${post.id}",
                modifier = Modifier.padding(8.dp),
            )
            Text(
                text = "タイトル\n${post.title}",
                modifier = Modifier.padding(8.dp),
            )
            Text(
                text = "内容\n${post.body}",
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

