package com.example.gfu.myapplication.cats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {

    @Query("SELECT * FROM DatabaseCat")
    suspend fun getAllCats(): List<DatabaseCat>

    @Query("SELECT * FROM DatabaseCat WHERE url = :url")
    suspend fun findCat(url: String): DatabaseCat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCat(post: DatabaseCat)
}