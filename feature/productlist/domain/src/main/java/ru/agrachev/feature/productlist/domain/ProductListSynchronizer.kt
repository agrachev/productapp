package ru.agrachev.feature.productlist.domain

interface ProductListSynchronizer {

    suspend fun requestSyncProducts()
}
