package ru.agrachev.core.presentation.navigation

import androidx.compose.runtime.Immutable

@Immutable
class NavEntryPointProviders(
    val root: Set<NavEntryPointProvider>,
    val nested: Set<NavProvider>
)
