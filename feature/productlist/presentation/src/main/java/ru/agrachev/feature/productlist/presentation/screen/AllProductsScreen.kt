package ru.agrachev.feature.productlist.presentation.screen

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.agrachev.core.presentation.OnProductCardClickedCallback
import ru.agrachev.core.presentation.screen.ProductListScreenTemplate
import ru.agrachev.feature.productlist.presentation.component.FilterBox
import ru.agrachev.feature.productlist.presentation.component.rememberCategoryPickerState
import ru.agrachev.feature.productlist.presentation.viewmodel.MainViewModel

@Composable
internal fun AllProductsScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onProductCardClicked: OnProductCardClickedCallback,
) {
    ProductListScreenTemplate(
        onProductCardClicked,
        viewModel,
    ) { itemList ->
        val categories by remember {
            derivedStateOf {
                itemList.itemSnapshotList.items.map { it.category }
                    .toSet()
            }
        }
        val categoryPickerState = rememberCategoryPickerState(categories)
        FilterBox(
            categoryPickerState = categoryPickerState,
            onSearchValueChanged = {
                viewModel.filter(it)
            },
            onCategoriesUpdated = {
                viewModel.filter(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        )
    }
}
