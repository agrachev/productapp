package ru.agrachev.core.presentation.mapper

import ru.agrachev.core.domain.entity.Product
import ru.agrachev.core.domain.entity.Rating
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.model.RatingUiModel

fun Product.toUiModel() = ProductUiModel(
    id = this.id,
    title = this.title,
    price = this.price,
    description = this.description,
    category = this.category,
    imageURL = this.imageURL,
    rating = this.rating.toUiModel(),
    isInFavorites = this.isInFavorites,
)

fun Rating.toUiModel() = RatingUiModel(
    rate = this.rate,
    count = this.count,
)

fun ProductUiModel.toDomainModel() = Product(
    id = this.id,
    title = this.title,
    price = this.price,
    description = this.description,
    category = this.category,
    imageURL = this.imageURL,
    rating = this.rating.toDomainModel(),
    isInFavorites = this.isInFavorites,
)

fun RatingUiModel.toDomainModel() = Rating(
    rate = this.rate,
    count = this.count,
)
