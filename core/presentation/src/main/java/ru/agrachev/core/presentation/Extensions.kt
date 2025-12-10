package ru.agrachev.core.presentation

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import ru.agrachev.core.presentation.navigation.NavEntry

fun String.capitalizeWords(): String = this.split(" ")
    .joinToString(" ") {
        it.capitalize(
            Locale.current
        )
    }

inline val <T : NavEntry<*>> Iterable<T>.startDestination
    get() = (this.firstOrNull { it.item.isStart } ?: first()).item.destination.toString()

fun Boolean.toFloat() = this.compareTo(false).toFloat()
