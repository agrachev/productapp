package ru.agrachev.feature.mainscreen.presentation.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.core.presentation.startDestination
import ru.agrachev.feature.mainscreen.presentation.component.BottomBar

@Composable
internal fun ProductListScreen(
    rootNavController: NavController,
    navEntryPointProviders: NavEntryPointProviders,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    val bottomBarNavProviders = navEntryPointProviders.nested
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController,
                bottomBarNavProviders,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = bottomBarNavProviders.startDestination,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            bottomBarNavProviders.forEach {
                it.graph(
                    this,
                    rootNavController,
                    navEntryPointProviders,
                    sharedTransitionScope,
                    animatedContentScope,
                )
            }
        }
    }
}
