package com.example.productviewersample.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.productviewersample.TAG
import com.example.productviewersample.data.local.FavouritesDB
import com.example.productviewersample.data.local.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideFavouritesDb(@ApplicationContext context: Context): FavouritesDB{
        Log.d(TAG, "provideDb")
        return Room.databaseBuilder(
            context,
         FavouritesDB::class.java,
          "favourite_products"
        ).build()
    }

    @Provides
    fun provideFavouritesDao(database: FavouritesDB): FavouritesDao{
        Log.d(TAG, "provideDao")
        return database.favouritesDao()
    }
}