package com.example.gfu.myapplication.home

import com.example.gfu.myapplication.cats.Cat
import com.example.gfu.myapplication.user.User

data class HomeUiState(
    val posts: List<Cat>,
    val loading: Boolean
)