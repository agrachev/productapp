package ru.agrachev.feature.favorites.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.core.presentation.navigation.NavProvider
import ru.agrachev.feature.favorites.navigation.FavoritesDestination
import ru.agrachev.feature.favorites.presentation.R
import ru.agrachev.feature.favorites.presentation.screen.FavoritesScreen
import ru.agrachev.feature.productcard.navigation.ProductCardDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
internal class FavoritesNavProvider @Inject constructor() : NavProvider() {

    override val item = BottomBarItem(
        ordinal = 1,
        labelResId = R.string.lbl_favorites,
        icon = Icons.Filled.Star,
        destination = FavoritesDestination,
    )

    override fun graph(
        builder: NavGraphBuilder,
        navController: NavController,
        navEntryPointProviders: NavEntryPointProviders,
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope?,
    ) = builder.buildBottomNavigationEntry {
        FavoritesScreen(sharedTransitionScope, animatedContentScope ?: it) { productId ->
            navController.navigate(ProductCardDestination.routeFor(productId))
        }
    }
}
