package ru.agrachev.feature.productcard.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import ru.agrachev.core.presentation.LocalSharedTransitionScope
import ru.agrachev.core.presentation.OnFavoritesClickedCallback
import ru.agrachev.core.presentation.ProductUiModelProvider
import ru.agrachev.core.presentation.R
import ru.agrachev.core.presentation.capitalizeWords
import ru.agrachev.core.presentation.component.FavoriteIconButton
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.sharedElement
import ru.agrachev.core.presentation.util.SharedContent
import ru.agrachev.feature.productcard.presentation.UiState
import ru.agrachev.feature.productcard.presentation.component.ProductFetchErrorAlert
import ru.agrachev.feature.productcard.presentation.component.RatingBar
import ru.agrachev.feature.productcard.presentation.viewmodel.ProductCardViewModel

@Composable
internal fun ProductCardScreen(
    viewModel: ProductCardViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        val uiState by viewModel.itemFlow.collectAsStateWithLifecycle()
        uiState?.let {
            when (it) {
                is UiState.DataAvailable -> {
                    val model = it.product
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        ProductInfoShort(
                            model = model,
                            onFavoritesClicked = { product ->
                                viewModel.toggleFavorite(product)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max),
                        )
                        AnimatedVisibility(
                            visible = !LocalSharedTransitionScope.current.isTransitionActive,
                            enter = fadeIn(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessHigh,
                                ),
                            ),
                            exit = ExitTransition.None,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            ProductDescriptionExtended {
                                model
                            }
                        }
                    }
                }

                is UiState.DataError -> ProductFetchErrorAlert(
                    it.throwable,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private inline fun ProductInfoShort(
    model: ProductUiModel,
    crossinline onFavoritesClicked: OnFavoritesClickedCallback,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        AsyncImage(
            model = model.imageURL,
            contentDescription = null,
            modifier = Modifier
                .sharedElement(
                    key = SharedContent.Image.key(model),
                )
                .width(120.dp)
                .fillMaxHeight(),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            Text(
                text = model.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .sharedElement(
                        key = SharedContent.Title.key(model),
                    )
                    .fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.Bottom),
            ) {
                Text(
                    text = stringResource(
                        R.string.lbl_price_format,
                        model.price,
                    ),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .sharedElement(
                            key = SharedContent.Price.key(model),
                        ),
                )
                FavoriteIconButton(
                    model = model,
                    onFavoritesClicked = onFavoritesClicked,
                )
            }
        }
    }
}

@Composable
private inline fun ProductDescriptionExtended(
    modifier: Modifier = Modifier,
    productUiModelProvider: ProductUiModelProvider,
) {
    productUiModelProvider().also { model ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier,
        ) {
            Text(
                text = model.category.capitalizeWords(),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            )
            Text(
                text = model.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            RatingBar(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                model.rating
            }
        }
    }
}
