package ru.agrachev.productapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.agrachev.core.presentation.navigation.NavEntryPointProvider
import ru.agrachev.core.presentation.navigation.NavEntryPointProviders
import ru.agrachev.core.presentation.navigation.NavProvider
import ru.agrachev.core.presentation.theme.ProductAppTheme
import ru.agrachev.feature.mainscreen.presentation.screen.MainScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomBarNavProviders: Set<@JvmSuppressWildcards NavProvider>

    @Inject
    lateinit var navEntryPointProvider: Set<@JvmSuppressWildcards NavEntryPointProvider>

    private val navEntryPointProviders by lazy(
        mode = LazyThreadSafetyMode.NONE,
    ) {
        NavEntryPointProviders(
            root = navEntryPointProvider,
            nested = bottomBarNavProviders.toSortedSet(Comparator { o1, o2 ->
                o1.item.ordinal.compareTo(
                    o2.item.ordinal
                )
            })
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppTheme {
                MainScreen(navEntryPointProviders)
            }
        }
    }
}
