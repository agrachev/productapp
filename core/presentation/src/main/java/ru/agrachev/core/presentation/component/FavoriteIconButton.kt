package ru.agrachev.core.presentation.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.util.SharedContent

@Composable
fun FavoriteIconButton(
    productUiModel: ProductUiModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onFavoritesClicked: (ProductUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    with(sharedTransitionScope) {
        IconButton(
            onClick = {
                onFavoritesClicked(productUiModel)
            },
            modifier = modifier then Modifier
                .sharedElement(
                    sharedTransitionScope
                        .rememberSharedContentState(
                            key = SharedContent.FavoriteButton.key(productUiModel),
                        ),
                    animatedVisibilityScope = animatedContentScope,
                ),
        ) {
            Icon(
                imageVector = if (productUiModel.isInFavorites) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
