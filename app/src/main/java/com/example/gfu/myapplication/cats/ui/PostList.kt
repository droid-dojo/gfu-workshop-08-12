package com.example.gfu.myapplication.cats.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.gfu.myapplication.cats.Cat

@Composable
fun PostList(posts: List<Cat>, onPostLiked: (Cat) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(posts) {
            PostCard(post = it, onLike = { onPostLiked(it) })
        }
    }
}