package com.example.productviewersample.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ProductEntity(
    val id:Int,
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val inFavourites: Boolean = false,
)
