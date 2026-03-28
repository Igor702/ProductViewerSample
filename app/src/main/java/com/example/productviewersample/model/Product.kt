package com.example.productviewersample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val title: String,
    val price: Double,
    val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "in_favourites") val inFavourites: Boolean = false,
)