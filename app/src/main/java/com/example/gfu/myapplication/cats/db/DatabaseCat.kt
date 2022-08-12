package com.example.gfu.myapplication.cats.db

import androidx.room.*

@Entity
data class DatabaseCat(
    @PrimaryKey(autoGenerate = true) val postId: Int = 0,
    val url: String,
    val likes: Int,
    val comments: Int,
    @Embedded(prefix = "user_") val user: DatabaseUser
)


