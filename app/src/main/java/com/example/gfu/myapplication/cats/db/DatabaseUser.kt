package com.example.gfu.myapplication.cats.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseUser(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val name: String,
    val location: String
)