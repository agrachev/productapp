package ru.agrachev.feature.mainscreen.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.agrachev.core.presentation.navigation.NavProvider

@Composable
internal fun BottomBar(
    navController: NavController,
    bottomBarNavProviders: Set<NavProvider>,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomBarNavProviders
            .map { it.item }
            .forEach {
                val labelText = stringResource(it.labelResId)
                NavigationBarItem(
                    selected = currentDestination?.belongsTo(it.destination.toString()) ?: false,
                    label = {
                        Text(
                            text = labelText,
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = labelText,
                        )
                    },
                    onClick = {
                        navController.navigate(it.destination.toString()) {
                            popUpTo(0) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
    }
}

private fun NavDestination.belongsTo(route: String) =
    hierarchy.any { it.route == route }
