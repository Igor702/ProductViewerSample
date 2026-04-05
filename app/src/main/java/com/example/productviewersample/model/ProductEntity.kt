package com.example.productviewersample.model

data class ProductEntity(
    val id:Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val imageUrl: String,
    val inFavourites: Boolean,
)
