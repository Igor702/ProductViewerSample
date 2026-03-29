package com.example.productviewersample.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productviewersample.model.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourite_products")
    fun getAllFavourites(): Flow<List<FavouriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourites(vararg product: FavouriteEntity)

    @Delete
    suspend fun deleteFromFavourites(product: FavouriteEntity)

}