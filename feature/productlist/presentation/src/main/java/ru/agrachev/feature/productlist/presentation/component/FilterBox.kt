package ru.agrachev.feature.productlist.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun FilterBox(
    categoryPickerState: CategoryPickerState,
    onSearchValueChanged: (String) -> Unit,
    onCategoriesUpdated: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        val searchCallbackState by rememberUpdatedState(onSearchValueChanged)
        val textFieldState = rememberTextFieldState()

        OutlinedTextField(
            lineLimits = TextFieldLineLimits.SingleLine,
            shape = CircleShape,
            state = textFieldState,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                )
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        )
        IconButton(
            onClick = {
                categoryPickerState.expanded = true
            },
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null,
            )
            CategoryPicker(
                categoryPickerState = categoryPickerState,
                onCategoriesUpdated = onCategoriesUpdated,
            )
        }
        LaunchedEffect(textFieldState) {
            snapshotFlow {
                textFieldState.text
            }.collect {
                searchCallbackState(it.toString())
            }
        }
    }
}
