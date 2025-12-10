package ru.agrachev.feature.productcard.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.agrachev.core.presentation.navigation.NavEntryPointProvider
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.feature.productcard.navigation.PRODUCT_ID_HANDLE
import ru.agrachev.feature.productcard.navigation.ProductCardDestination
import ru.agrachev.feature.productcard.presentation.screen.ProductCardScreen
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
internal class ProductCardNavProvider @Inject constructor() : NavEntryPointProvider {

    override val item = NavEntryPointProvider.RouteItem(
        destination = ProductCardDestination,
    )

    override fun graph(
        builder: NavGraphBuilder,
        navController: NavController,
        navEntryPointProviders: NavEntryPointProviders,
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope?,
    ) = builder.run {
        composable(
            route = item.destination.toString(),
            arguments = listOf(navArgument(PRODUCT_ID_HANDLE) {
                type = NavType.IntType
            }),
        ) {
            ProductCardScreen(
                sharedTransitionScope,
                this,
            )
        }
    }
}
