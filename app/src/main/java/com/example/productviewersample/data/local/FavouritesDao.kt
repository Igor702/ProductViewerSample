package com.example.productviewersample.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.productviewersample.model.Product

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourite_products")
    fun getAllFavourites(): List<Product>

    @Query("SELECT * FROM favourite_products WHERE id IN (:favouriteId)")
    fun getFavouriteProductById(favouriteId: Int): Product

    @Insert
    fun addToFavourites(product: Product)

    @Delete
    fun deleteFromFavourites(product: Product)

}