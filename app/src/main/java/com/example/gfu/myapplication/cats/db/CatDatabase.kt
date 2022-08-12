package com.example.gfu.myapplication.cats.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseCat::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun cats(): CatDao
}