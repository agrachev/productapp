package ru.agrachev.core.data.remote.dto

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class RatingDto(
    val rate: Float,
    val count: Int,
)
