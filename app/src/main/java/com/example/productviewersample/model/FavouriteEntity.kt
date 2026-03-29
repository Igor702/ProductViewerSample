package com.example.productviewersample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_products")
data class FavouriteEntity(
    @PrimaryKey val id:Int,
    val title: String,
)