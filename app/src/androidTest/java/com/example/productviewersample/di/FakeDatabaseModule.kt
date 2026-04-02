package com.example.productviewersample.di

import android.content.Context
import androidx.room.Room
import com.example.productviewersample.data.local.FavouritesDB
import com.example.productviewersample.data.local.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn( components = [SingletonComponent::class],
    replaces = [DatabaseModule::class])
object FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideFavouritesDb(@ApplicationContext context: Context): FavouritesDB{
        return Room.inMemoryDatabaseBuilder(
            context = context,
            klass = FavouritesDB::class.java,
        )
            .allowMainThreadQueries()
            .build()

    }

    @Provides
    @Singleton
    fun provideTestNotesDao(database: FavouritesDB): FavouritesDao{
        return database.favouritesDao()
    }
}