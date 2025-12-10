package ru.agrachev.core.presentation.navigation

import ru.agrachev.core.navigation.Destination

interface NavEntryPointProvider : NavEntry<NavEntryPointProvider.RouteItem> {

    data class RouteItem(
        override val destination: Destination,
        override val isStart: Boolean = false,
    ) : NavEntry.NavItem
}
