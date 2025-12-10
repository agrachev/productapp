package ru.agrachev.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import ru.agrachev.core.presentation.R
import ru.agrachev.core.presentation.model.ProductUiModel

@Composable
internal fun PagingDataLoadingErrorItem(
    productList: LazyPagingItems<ProductUiModel>,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier then Modifier
            .height(IntrinsicSize.Max),
    ) {
        Text(
            text = stringResource(R.string.lbl_error),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f),
        )
        Button(
            onClick = {
                productList.refresh()
            },
        ) {
            Text(
                text = stringResource(R.string.lbl_reload),
            )
        }
    }
}
