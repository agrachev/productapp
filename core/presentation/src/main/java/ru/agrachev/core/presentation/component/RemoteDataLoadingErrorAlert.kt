package ru.agrachev.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import ru.agrachev.core.presentation.R
import ru.agrachev.core.presentation.model.ProductUiModel

@Composable
internal fun RemoteDataLoadingErrorAlert(
    productList: LazyPagingItems<ProductUiModel>,
    throwable: Throwable,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.lbl_error),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            throwable.message?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
            Button(
                onClick = {
                    productList.refresh()
                },
                modifier = Modifier
                    .fillMaxWidth(fraction = .4f)
            ) {
                Text(
                    text = stringResource(R.string.lbl_reload),
                )
            }
        }
    }
}
