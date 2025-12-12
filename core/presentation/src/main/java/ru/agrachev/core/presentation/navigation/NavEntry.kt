package ru.agrachev.core.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.agrachev.core.navigation.Destination

interface NavEntry<T : NavEntry.NavItem> {

    val item: T

    fun graph(
        builder: NavGraphBuilder,
        navController: NavController,
        navEntryPointProviders: NavEntryPointProviders,
        animatedContentScope: AnimatedContentScope? = null,
    )

    interface NavItem {
        val destination: Destination
        val isStart: Boolean
    }
}
