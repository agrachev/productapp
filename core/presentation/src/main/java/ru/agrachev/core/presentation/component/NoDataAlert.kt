package ru.agrachev.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.agrachev.core.presentation.R

@Composable
internal fun NoDataAlert(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.lbl_no_data_to_display),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
