package ru.agrachev.core.presentation.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.agrachev.core.presentation.R
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.util.SharedContent

@Composable
internal fun ProductCard(
    model: ProductUiModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onFavoritesClicked: (ProductUiModel) -> Unit,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    with(sharedTransitionScope) {
        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .clickable {
                    onItemClicked(model.id)
                }
        ) {
            Box(
                modifier = modifier then Modifier,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(8.dp),
                ) {
                    AsyncImage(
                        model = model.imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .sharedElement(
                                sharedTransitionScope
                                    .rememberSharedContentState(
                                        key = SharedContent.Image.key(model),
                                    ),
                                animatedVisibilityScope = animatedContentScope,
                            )
                            .weight(1f)
                            .fillMaxHeight(),
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .weight(4f)
                            .fillMaxHeight(),
                    ) {
                        Text(
                            text = model.title,
                            modifier = Modifier
                                .sharedElement(
                                    sharedTransitionScope
                                        .rememberSharedContentState(
                                            key = SharedContent.Title.key(model),
                                        ),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                                .fillMaxWidth(),
                        )
                        Text(
                            text = stringResource(R.string.price_format, model.price),
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            modifier = Modifier
                                .sharedElement(
                                    sharedTransitionScope
                                        .rememberSharedContentState(
                                            key = SharedContent.Price.key(model),
                                        ),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                                .fillMaxWidth()
                                .wrapContentHeight(Alignment.Bottom)
                                .weight(1f),
                        )
                    }
                }
                FavoriteIconButton(
                    model,
                    sharedTransitionScope,
                    animatedContentScope,
                    onFavoritesClicked,
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                )
            }
        }
    }
}
