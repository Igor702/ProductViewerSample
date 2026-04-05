package com.example.productviewersample.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productviewersample.model.ProductEntity


@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    list: MutableList<ProductEntity>,
    onFavouritesChange: (Int) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = list) { product ->
            ProductsListItem(
                modifier = modifier,
                id = product.id,
                title = product.title,
                price = product.price,
                category = product.category,
                imageUrl = product.imageUrl,
                inFavourites = product.inFavourites,
                onFavouritesChange = { id ->
                    onFavouritesChange(id)
                },
                onNavigateToDetails = {})


        }
    }

}


@Preview
@Composable
private fun ProductsListPreview() {
    val products = mutableListOf<ProductEntity>()
    for (i in 0..20) {
        products.add(
            ProductEntity(
                id = i,
                title = "title $i",
                price = i.toDouble(),
                description = "$i",
                category = "category $i",
                imageUrl = "",
                inFavourites = false
            )
        )
    }
    MaterialTheme {
        Surface { ProductsList(list = products) {} }
    }
}