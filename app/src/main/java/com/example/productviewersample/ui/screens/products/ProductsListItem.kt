package com.example.productviewersample.ui.screens.products

import android.icu.text.NumberFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productviewersample.R


@Composable
fun ProductsListItem(
    modifier: Modifier = Modifier,
    title: String,
    price: Double,
    category: String,
    imageUrl: String,
    inFavourites: Boolean,
    onFavouritesChange: () -> Unit,
    onNavigateToDetails: () -> Unit,
) {

    Surface(
        shape = ShapeDefaults.Medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .clickable(
                onClick = onNavigateToDetails)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(modifier = Modifier.weight(1f)) {

                AsyncImage(
                    model = if (LocalInspectionMode.current) {
                        R.drawable.ic_launcher_background
                    } else {
                        imageUrl
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                ProductTextDescription(
                    Modifier.heightIn(max = 100.dp),
                    title = title,
                    price = price,
                    category = category)
            }

            AddRemoveIcon(
                inFavourites = inFavourites,
                onFavouritesChange = onFavouritesChange
            )


        }
    }


}

/**
 * ProductTextInfo is a reusable composable for displaying all text information
 * about Product. Has small form for ProductsList and, if description is set,
 * large form to display a description.
 */
@Composable
fun ProductTextDescription(
    modifier: Modifier,
    title: String,
    price: Double,
    category: String,
    description: String = ""
) {
    Column(modifier.padding(8.dp)) {

        Text(style = MaterialTheme.typography.titleLarge, text = title)
        Spacer(Modifier.height(8.dp))

        KeyValueTextComponent(
            key = stringResource(R.string.category),
            value = category
        )

        KeyValueTextComponent(
            key = stringResource(R.string.price),
            value = NumberFormat.getCurrencyInstance().format(price),
            style = MaterialTheme.typography.bodyMedium
        )

        if (description.isNotEmpty()) {

            Spacer(Modifier.height(24.dp))

            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.about_this_product)
            )

            Text(text = description)
        }
    }
}


@Composable
fun AddRemoveIcon(inFavourites: Boolean, onFavouritesChange: () -> Unit) {
    if (inFavourites) {
        Image(
            imageVector = Icons.Default.Favorite,
            contentDescription =
                stringResource(R.string.add_the_product_to_favourites),
            Modifier.clickable(onClick = onFavouritesChange)
        )
    } else {
        Image(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription =
                stringResource(R.string.remove_the_product_from_favourites),
            Modifier.clickable(onClick = onFavouritesChange)
        )
    }
}

@Composable
fun KeyValueTextComponent(
    key: String,
    value: String,
    style: TextStyle = LocalTextStyle.current
) {

    Row {
        Text(text = "$key: ", style = style)
        Text(text = value, style = style)
    }

}

@Preview
@Composable
private fun ProductsListItemPreview() {
    MaterialTheme {
        Surface {
            ProductsListItem(
                title = "DefaultPhone A5",
                price = 1200.99,
                category = "Flagman",
                imageUrl = "",
                inFavourites = true,
                onFavouritesChange = {},
            ) {}

        }
    }
}


@Preview
@Composable
private fun ProductTextDescriptionPreview() {

    MaterialTheme {
        Surface {
            ProductTextDescription(
                Modifier.height(100.dp),
                "DefaultPhone A5",
                1200.99,
                "Flagman"
            )

        }
    }

}

@Preview
@Composable
private fun KeyValueTextComponentPreview() {
    MaterialTheme {
        Surface {
            KeyValueTextComponent(
                key = "Price",
                value = NumberFormat
                    .getCurrencyInstance().format(1200.99))

        }
    }
}