package ru.agrachev.core.data.remote.mapper

import ru.agrachev.core.data.remote.dto.ProductDto
import ru.agrachev.core.data.remote.dto.RatingDto
import ru.agrachev.core.domain.entity.Product
import ru.agrachev.core.domain.entity.Rating

fun ProductDto.toDomainModel() = Product(
    id = this.id,
    title = this.title,
    price = this.price,
    description = this.description,
    category = this.category,
    imageURL = this.image,
    rating = this.rating.toDomainModel(),
)

fun RatingDto.toDomainModel() = Rating(
    rate = this.rate,
    count = this.count,
)
