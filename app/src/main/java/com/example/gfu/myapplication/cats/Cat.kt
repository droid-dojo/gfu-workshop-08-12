package com.example.gfu.myapplication.cats

import com.example.gfu.myapplication.user.User

data class Cat(
    val author: User,
    val url: String,
    val likes: Int,
    val comments: Int,
)
