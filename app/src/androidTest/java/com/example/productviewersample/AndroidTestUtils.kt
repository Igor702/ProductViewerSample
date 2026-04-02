package com.example.productviewersample

import com.example.productviewersample.model.FavouriteEntity

const val TAG = "TAG"

private val products = mutableListOf<FavouriteEntity>(
    FavouriteEntity(
        id = 0,
        title = "title0",
    ),
    FavouriteEntity(
        id = 1,
        title = "title1",
    )
)

fun getFavouritesList():List<FavouriteEntity>{
return products
}