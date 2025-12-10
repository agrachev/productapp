package ru.agrachev.feature.mainscreen.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
        sharedTransitionScope: SharedTransitionScope,
        animatedContentScope: AnimatedContentScope?,
    ) = builder.run {
        composable(
            route = item.destination.toString(),
        ) {
            ProductListScreen(
                navController,
                navEntryPointProviders,
                sharedTransitionScope,
                this,
            )
        }
    }
}
