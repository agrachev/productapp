package ru.agrachev.feature.productlist.presentation.component

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import kotlinx.parcelize.Parcelize
import ru.agrachev.core.presentation.capitalizeWords

@Composable
internal fun CategoryPicker(
    categoryPickerState: CategoryPickerState,
    onCategoriesUpdated: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    with(categoryPickerState) {
        DropdownMenu(
            expanded = expanded,
            shape = RoundedCornerShape(16.dp),
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            offset = DpOffset(x = 0.dp, y = 8.dp),
            onDismissRequest = {
                expanded = false
            },
            modifier = modifier,
        ) {
            categories.forEach {
                key(it) {
                    var checked by rememberSaveable {
                        mutableStateOf(it !in excludeCategories)
                    }
                    DropdownMenuItem(
                        onClick = {
                            checked = !checked
                            if (checked) {
                                excludeCategories.remove(it)
                            } else {
                                excludeCategories.add(it)
                            }
                            onCategoriesUpdated(excludeCategories.toSet())
                        },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .width(IntrinsicSize.Max),
                            ) {
                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = null
                                )
                                Text(
                                    text = it.capitalizeWords(),
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun rememberCategoryPickerState(categories: Set<String>) =
    rememberSaveable(saver = CategoryPickerState.Saver) {
        CategoryPickerState(
            initialCategories = categories,
            initialExpanded = false,
        )
    }.apply {
        this.categories.addAll(categories)
    }

@Immutable
class CategoryPickerState internal constructor(
    initialCategories: Set<String>,
    initialExpanded: Boolean,
    internal val excludeCategories: MutableSet<String> = mutableSetOf(),
) {
    val categories = mutableStateSetOf(*initialCategories.toTypedArray())
    var expanded by mutableStateOf(initialExpanded)

    internal companion object {
        val Saver = object : Saver<CategoryPickerState, Saveable> {
            override fun SaverScope.save(value: CategoryPickerState) = Saveable(
                categories = value.categories.toSet(),
                expanded = value.expanded,
                excludeCategories = value.excludeCategories,
            )

            override fun restore(value: Saveable) = CategoryPickerState(
                initialCategories = value.categories,
                initialExpanded = value.expanded,
                excludeCategories = value.excludeCategories.toMutableSet(),
            )
        }
    }

    @Parcelize
    internal class Saveable(
        val categories: Set<String>,
        val expanded: Boolean,
        val excludeCategories: Set<String>,
    ) : Parcelable
}


