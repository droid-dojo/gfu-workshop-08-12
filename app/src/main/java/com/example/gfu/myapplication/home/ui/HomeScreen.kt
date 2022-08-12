package com.example.gfu.myapplication.home.ui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gfu.myapplication.home.HomeScreenViewModel
import com.example.gfu.myapplication.cats.Cat
import com.example.gfu.myapplication.cats.ui.PostList
import com.example.gfu.myapplication.home.HomeUiState

@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = viewModel()
    val state = viewModel.uiState.collectAsState().value

    HomeScreen(
        state = state,
        onPostLiked = {
            viewModel.likeCat(it)
        }
    )
}

@Composable
fun HomeScreen(state: HomeUiState, onPostLiked: (Cat) -> Unit = {}) {
    PostList(posts = state.posts, onPostLiked = onPostLiked)

    if (state.loading) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun HomeScreenLoadingPreview() {
    HomeScreen(
        HomeUiState(loading = true, posts = emptyList())
    )
}
