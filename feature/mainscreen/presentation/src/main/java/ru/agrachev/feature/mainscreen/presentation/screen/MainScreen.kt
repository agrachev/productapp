package ru.agrachev.feature.mainscreen.presentation.screen

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.core.presentation.startDestination
import ru.agrachev.feature.mainscreen.presentation.connectivity.component.ConnectivityPanel

@Composable
fun MainScreen(
    navEntryPointProviders: NavEntryPointProviders,
) {
    SharedTransitionLayout {
        val rootNavController = rememberNavController()
        val navEntryPointProvider = navEntryPointProviders.root
        NavHost(
            navController = rootNavController,
            startDestination = navEntryPointProvider.startDestination,
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
        ) {
            navEntryPointProvider.forEach {
                it.graph(
                    this,
                    rootNavController,
                    navEntryPointProviders,
                    this@SharedTransitionLayout,
                )
            }
        }
        ConnectivityPanel(
            panelHeightDp = 24.dp,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
