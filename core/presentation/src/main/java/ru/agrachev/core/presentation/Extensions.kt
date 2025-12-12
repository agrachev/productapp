package ru.agrachev.core.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SharedTransitionDefaults
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SharedTransitionScope.PlaceholderSize
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.SharingStarted
import ru.agrachev.core.presentation.model.ProductUiModel
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

@Suppress("FunctionName")
fun SharingStarted.Companion.WhileSubscribedWithDelay() =
    SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS)

private const val STOP_TIMEOUT_MILLIS = 5_000L


val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope> {
    error(message = "SharedTransitionScope is not provided")
}
val LocalAnimatedContentScope = compositionLocalOf<AnimatedContentScope> {
    error(message = "AnimatedContentScope is not provided")
}

typealias ProductUiModelProvider = () -> ProductUiModel
typealias OnFavoritesClickedCallback = (ProductUiModel) -> Unit
typealias OnProductCardClickedCallback = (Int) -> Unit

private typealias Animation<T> =
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards T?

@Suppress("detekt:LongParameterList")
inline fun NavGraphBuilder.animatedScopeComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: Animation<EnterTransition>? = null,
    noinline exitTransition: Animation<ExitTransition>? = null,
    noinline popEnterTransition: Animation<EnterTransition>? = enterTransition,
    noinline popExitTransition: Animation<ExitTransition>? = exitTransition,
    noinline sizeTransform: Animation<SizeTransform>? = { SizeTransform(clip = false) },
    rootAnimatedContentScope: AnimatedContentScope? = null,
    crossinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(
    route,
    arguments,
    deepLinks,
    enterTransition,
    exitTransition,
    popEnterTransition,
    popExitTransition,
    sizeTransform,
) {
    CompositionLocalProvider(
        LocalAnimatedContentScope provides (rootAnimatedContentScope ?: this),
    ) {
        content(it)
    }
}

@Composable
fun Modifier.sharedElement(
    key: Any,
    boundsTransform: BoundsTransform = SharedTransitionDefaults.BoundsTransform,
    placeholderSize: PlaceholderSize = PlaceholderSize.ContentSize,
    renderInOverlayDuringTransition: Boolean = true,
    zIndexInOverlay: Float = 0f,
) = if (LocalInspectionMode.current) this
else with(LocalSharedTransitionScope.current) {
    sharedElement(
        sharedContentState = rememberSharedContentState(key),
        animatedVisibilityScope = LocalAnimatedContentScope.current,
        boundsTransform = boundsTransform,
        placeholderSize = placeholderSize,
        renderInOverlayDuringTransition = renderInOverlayDuringTransition,
        zIndexInOverlay = zIndexInOverlay,
    )
}
