package ru.agrachev.core.domain.service

import ru.agrachev.core.domain.entity.Product

interface FavoriteStatusUpdater {

    suspend fun markAsFavorite(product: Product, isFavorite: Boolean)
}
