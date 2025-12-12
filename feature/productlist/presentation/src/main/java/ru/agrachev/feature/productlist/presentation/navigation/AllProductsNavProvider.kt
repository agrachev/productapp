package ru.agrachev.feature.productlist.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.core.presentation.navigation.NavProvider
import ru.agrachev.feature.productcard.navigation.ProductCardDestination
import ru.agrachev.feature.productlist.navigation.ProductListDestination
import ru.agrachev.feature.productlist.presentation.R
import ru.agrachev.feature.productlist.presentation.screen.AllProductsScreen
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
internal class AllProductsNavProvider @Inject constructor() : NavProvider() {

    override val item = BottomBarItem(
        ordinal = 0,
        labelResId = R.string.lbl_products,
        icon = Icons.Filled.ShoppingBag,
        destination = ProductListDestination,
        isStart = true,
    )

    override fun graph(
        builder: NavGraphBuilder,
        navController: NavController,
        navEntryPointProviders: NavEntryPointProviders,
        animatedContentScope: AnimatedContentScope?
    ) = builder.buildBottomNavigationEntry(animatedContentScope) {
        AllProductsScreen { productId ->
            navController.navigate(ProductCardDestination.routeFor(productId))
        }
    }
}
