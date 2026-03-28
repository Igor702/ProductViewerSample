package com.example.productviewersample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productviewersample.model.Product

@Database(entities = [Product::class], version = 1)
abstract class FavouritesDB: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}