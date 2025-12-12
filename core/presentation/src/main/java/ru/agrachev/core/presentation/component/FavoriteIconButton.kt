package ru.agrachev.core.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.agrachev.core.presentation.OnFavoritesClickedCallback
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.sharedElement
import ru.agrachev.core.presentation.util.SharedContent

@Composable
inline fun FavoriteIconButton(
    model: ProductUiModel,
    crossinline onFavoritesClicked: OnFavoritesClickedCallback,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = {
            onFavoritesClicked(model)
        },
        modifier = modifier then Modifier
            .sharedElement(
                key = SharedContent.FavoriteButton.key(model),
            ),
    ) {
        Icon(
            imageVector = if (model.isInFavorites) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}
