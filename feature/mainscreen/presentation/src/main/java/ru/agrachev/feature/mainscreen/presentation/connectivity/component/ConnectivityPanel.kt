package ru.agrachev.feature.mainscreen.presentation.connectivity.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.agrachev.core.presentation.toFloat
import ru.agrachev.feature.mainscreen.presentation.R
import ru.agrachev.feature.mainscreen.presentation.connectivity.viewmodel.ConnectivityViewModel

@Composable
internal fun ConnectivityPanel(
    panelHeightDp: Dp,
    modifier: Modifier = Modifier,
    viewModel: ConnectivityViewModel = hiltViewModel(),
) {
    val panelOffsetFloat = with(LocalDensity.current) {
        -panelHeightDp.toPx()
    }
    val connectionStatus by viewModel.connectionStatusFlow.collectAsStateWithLifecycle()
    val animatedColor by animateColorAsState(
        targetValue = with(MaterialTheme.colorScheme) {
            if (connectionStatus.isConnected) primaryContainer else errorContainer
        },
    )

    val offsetAnimator = remember(panelOffsetFloat) {
        Animatable(panelOffsetFloat * connectionStatus.isConnected.toFloat())
    }
    Box(
        modifier = modifier then Modifier
            .height(panelHeightDp)
            .offset {
                IntOffset(x = 0, y = offsetAnimator.value.toInt())
            }
            .zIndex(1f)
            .drawBehind {
                drawRect(
                    color = animatedColor,
                )
            },
    ) {
        AnimatedContent(
            targetState = connectionStatus,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(
                    if (it.isConnected) {
                        R.string.lbl_back_online
                    } else {
                        R.string.lbl_offline_mode
                    }
                ),
                style = MaterialTheme.typography.titleMedium,
                color = with(MaterialTheme.colorScheme) {
                    if (it.isConnected) onPrimaryContainer else onErrorContainer
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        LaunchedEffect(Unit) {
            snapshotFlow {
                connectionStatus.isConnected
            }
                .distinctUntilChanged()
                .collect {
                    val valueMultiplier = it.toFloat()
                    offsetAnimator.animateTo(
                        targetValue = panelOffsetFloat * valueMultiplier,
                        animationSpec = tween(
                            delayMillis = PANEL_SLIDE_OUT_DELAY * valueMultiplier.toInt(),
                        ),
                    )
                }
        }
    }
}

private const val PANEL_SLIDE_OUT_DELAY = 3_000
