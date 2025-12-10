package ru.agrachev.feature.favorites.presentation.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.agrachev.core.presentation.screen.ProductListScreenTemplate
import ru.agrachev.feature.favorites.presentation.viewmodel.FavoritesViewModel

@Composable
internal fun FavoritesScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: FavoritesViewModel = hiltViewModel(),
    onProductCardClicked: (Int) -> Unit,
) {
    ProductListScreenTemplate(
        sharedTransitionScope,
        animatedContentScope,
        onProductCardClicked,
        viewModel,
    )
}
