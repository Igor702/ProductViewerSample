package com.example.productviewersample.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productviewersample.R
import com.example.productviewersample.model.ProductEntity


@Composable
fun ProductsScreenStateless(
    modifier: Modifier = Modifier,
    uiState: ProductsUiState,
    onFavouritesChange: (Int) -> Unit,
    onRetry: () -> Unit,
    onNavigate: (ProductEntity) -> Unit,
) {

    when (uiState) {
        is ProductsUiState.Success -> {
            ProductsSuccess(
                modifier = modifier,
                list = uiState.products,
                onFavouritesChange = onFavouritesChange,
                onNavigate = onNavigate
            )
        }

        is ProductsUiState.Loading -> {
            ProductsLoading(
                modifier = modifier
            )
        }

        is ProductsUiState.Error -> {
            ProductsError(
                modifier = modifier,
                error = uiState.errorMessage,
                onRetry = onRetry
            )
        }


    }

}

@Composable
fun ProductsError(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit
) {

    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )

            Text(
                text = stringResource(R.string.oops_something_get_wrong),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )

            KeyValueTextComponent(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                key = stringResource(R.string.error),
                value = error,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))
            Button(
                colors = ButtonDefaults.buttonColors(),
                onClick = onRetry
            ) {

                Text(
                    text = stringResource(R.string.try_again),
                    style = MaterialTheme.typography.titleMedium
                )
            }


        }
    }


}


@Composable
fun ProductsLoading(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(80.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        //Spacer height = CircularProgressIndicator R + 24.dp
        Spacer(Modifier.height(64.dp))
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
fun ProductsSuccess(
    modifier: Modifier = Modifier,
    list: List<ProductEntity>,
    onFavouritesChange: (Int) -> Unit,
    onNavigate: (ProductEntity) -> Unit
) {

    ProductsList(
        modifier = modifier,
        list = list,
        onFavouritesChange = onFavouritesChange,
        onNavigate = onNavigate
    )

}


@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    list: List<ProductEntity>,
    onFavouritesChange: (Int) -> Unit,
    onNavigate: (ProductEntity) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = list,
            key = { it.id }) { product ->
            ProductsListItem(
                modifier = Modifier.fillMaxWidth(),
                id = product.id,
                title = product.title,
                price = product.price,
                category = product.category,
                imageUrl = product.imageUrl,
                inFavourites = product.inFavourites,
                onFavouritesChange = onFavouritesChange,
                onNavigateToDetails = { onNavigate(product) })


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
        Surface {
            ProductsList(
                list = products,
                onFavouritesChange = {}) {}
        }
    }
}

@Preview
@Composable
private fun ProductsSuccessPreview() {

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
        Surface {
            ProductsSuccess(
                list = products,
                onFavouritesChange = {}) {}
        }
    }

}

@Preview
@Composable
private fun ProductsLoadingPreview() {
    MaterialTheme {
        Surface {
            ProductsLoading()

        }
    }
}


@Preview
@Composable
private fun ProductsErrorPreview() {
    MaterialTheme {
        Surface {
            ProductsError(
                error = "Something get wrong"
            ) { }
        }
    }

}