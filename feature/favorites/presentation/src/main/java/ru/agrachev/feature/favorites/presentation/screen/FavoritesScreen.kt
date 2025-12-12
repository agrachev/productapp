package ru.agrachev.feature.favorites.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.agrachev.core.presentation.OnProductCardClickedCallback
import ru.agrachev.core.presentation.screen.ProductListScreenTemplate
import ru.agrachev.feature.favorites.presentation.viewmodel.FavoritesViewModel

@Composable
internal fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onProductCardClicked: OnProductCardClickedCallback,
) {
    ProductListScreenTemplate(
        onProductCardClicked,
        viewModel,
    )
}
