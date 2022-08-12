package com.example.gfu.myapplication.cats.api


data class ApiCat(
    val created_at: String,
    val id: String,
    val tags: List<String>
)

