package com.example.gfu.myapplication.cats.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.gfu.myapplication.cats.Cat
import com.example.gfu.myapplication.user.ui.UserInfo

@Composable
fun PostCard(post: Cat, onLike: () -> Unit = {}) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column() {
            UserInfo(
                user = post.author,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
                model = post.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                },

                error = {
                    Text(it.result.throwable.message.orEmpty())
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onLike) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                    Text("${post.likes}")
                }

                TextButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    Text("${post.comments}")
                }
            }
        }
    }
}
