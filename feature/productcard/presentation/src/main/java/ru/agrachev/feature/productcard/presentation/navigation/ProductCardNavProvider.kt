package ru.agrachev.feature.productcard.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.agrachev.core.presentation.animatedScopeComposable
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
        animatedContentScope: AnimatedContentScope?,
    ) = builder.run {
        animatedScopeComposable(
            route = item.destination.toString(),
            arguments = listOf(navArgument(PRODUCT_ID_HANDLE) {
                type = NavType.IntType
            }),
        ) {
            ProductCardScreen()
        }
    }
}
