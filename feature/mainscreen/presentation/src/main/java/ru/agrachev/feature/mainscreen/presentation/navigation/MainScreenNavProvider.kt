package ru.agrachev.feature.mainscreen.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.agrachev.core.presentation.animatedScopeComposable
import ru.agrachev.core.presentation.navigation.NavEntryPointProvider
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.feature.mainscreen.navigation.MainScreenDestination
import ru.agrachev.feature.mainscreen.presentation.screen.ProductListScreen
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
internal class MainScreenNavProvider @Inject constructor() : NavEntryPointProvider {

    override val item = NavEntryPointProvider.RouteItem(
        destination = MainScreenDestination,
        isStart = true,
    )

    override fun graph(
        builder: NavGraphBuilder,
        navController: NavController,
        navEntryPointProviders: NavEntryPointProviders,
        animatedContentScope: AnimatedContentScope?,
    ) = builder.run {
        animatedScopeComposable(
            route = item.destination.toString(),
        ) {
            ProductListScreen(
                navController,
                navEntryPointProviders,
                this,
            )
        }
    }
}
