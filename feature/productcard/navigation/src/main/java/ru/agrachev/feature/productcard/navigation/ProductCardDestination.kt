package ru.agrachev.feature.productcard.navigation

import ru.agrachev.core.navigation.Destination

object ProductCardDestination : Destination("product_card/$PRODUCT_ID_WILDCARD") {

    fun routeFor(productId: Int) = toString()
        .replace(PRODUCT_ID_WILDCARD, productId.toString())
}

const val PRODUCT_ID_HANDLE = "product_id"

private const val PRODUCT_ID_WILDCARD = "{$PRODUCT_ID_HANDLE}"
