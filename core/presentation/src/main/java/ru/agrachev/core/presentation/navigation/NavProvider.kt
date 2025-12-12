package ru.agrachev.core.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navArgument
import ru.agrachev.core.navigation.Destination
import ru.agrachev.core.presentation.animatedScopeComposable

abstract class NavProvider : NavEntry<NavProvider.BottomBarItem> {

    data class BottomBarItem(
        val ordinal: Int,
        val labelResId: Int,
        val icon: ImageVector,
        override val destination: Destination,
        override val isStart: Boolean = false,
    ) : NavEntry.NavItem

    protected fun NavGraphBuilder.buildBottomNavigationEntry(
        animatedContentScope: AnimatedContentScope?,
        content: @Composable (AnimatedContentScope) -> Unit,
    ) = this.run {
        animatedScopeComposable(
            rootAnimatedContentScope = animatedContentScope,
            route = item.destination.toString(),
            arguments = listOf(navArgument(DEST_ARG_NAME) {
                defaultValue = item.ordinal
            }),
            enterTransition = {
                slideIntoContainer(
                    towards = slideDirection,
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = slideDirection,
                )
            },
        ) {
            content(this)
        }
    }

    private inline val AnimatedContentTransitionScope<NavBackStackEntry>.slideDirection
        get() = (this.initialState.destination.arguments[DEST_ARG_NAME]?.defaultValue as Int).let {
            if (it > item.ordinal) {
                AnimatedContentTransitionScope.SlideDirection.End
            } else {
                AnimatedContentTransitionScope.SlideDirection.Start
            }
        }
}

private const val DEST_ARG_NAME = "dest"
