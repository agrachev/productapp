package ru.agrachev.feature.productcard.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.agrachev.core.presentation.model.RatingUiModel
import ru.agrachev.feature.productcard.presentation.R
import ru.agrachev.feature.productcard.presentation.shape.FivePointStar

@Composable
internal inline fun RatingBar(
    modifier: Modifier = Modifier,
    ratingUiModelProvider: () -> RatingUiModel,
) {
    ratingUiModelProvider().run {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier then Modifier
                .height(IntrinsicSize.Max),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val starColor = MaterialTheme.colorScheme.primary
                repeat(MAX_RATING) {
                    val fraction = (rate - it).coerceIn(0f, 1f)
                    Canvas(
                        modifier = Modifier
                            .size(32.dp)
                            .border(
                                width = 1.dp,
                                color = starColor,
                                shape = FivePointStar,
                            )
                            .clip(FivePointStar),
                    ) {
                        drawRect(
                            color = starColor,
                            size = size.copy(width = size.width * fraction),
                        )
                    }
                }
            }
            Text(
                text = stringResource(R.string.lbl_rating_score, rate, count),
                style = MaterialTheme.typography.bodyMedium,
                autoSize = TextAutoSize.StepBased(),
                modifier = Modifier
                    .fillMaxHeight(),
            )
        }
    }
}

const val MAX_RATING = 5
