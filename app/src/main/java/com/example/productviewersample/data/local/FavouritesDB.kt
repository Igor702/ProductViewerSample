package com.example.productviewersample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productviewersample.model.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1, exportSchema = false)
abstract class FavouritesDB: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}