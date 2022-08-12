package com.example.gfu.myapplication.cats.api

import retrofit2.http.GET

interface CatApi {
    @GET("cats")
    suspend fun searchCats(): List<ApiCat>
}