package ru.agrachev.core.data.persistence.mapper

import ru.agrachev.core.data.persistence.entity.ProductPartialPayload
import ru.agrachev.core.data.persistence.entity.ProductWithRating
import ru.agrachev.core.data.persistence.entity.RatingEntity
import ru.agrachev.core.domain.entity.Product
import ru.agrachev.core.domain.entity.Rating

fun Product.toEntityModel() = ProductPartialPayload(
    id = this.id,
    title = this.title,
    price = this.price,
    description = this.description,
    category = this.category,
    imageURL = this.imageURL,
)

fun Rating.toEntityModel(parent: Product) = RatingEntity(
    id = parent.id,
    rate = this.rate,
    count = this.count,
)

fun ProductWithRating.toDomainModel() = Product(
    id = this.product.id,
    title = this.product.title,
    price = this.product.price,
    description = this.product.description,
    category = this.product.category,
    imageURL = this.product.imageURL,
    isInFavorites = this.product.isInFavorites == true,
    rating = Rating(
        rate = this.rating.rate,
        count = this.rating.count,
    ),
)
