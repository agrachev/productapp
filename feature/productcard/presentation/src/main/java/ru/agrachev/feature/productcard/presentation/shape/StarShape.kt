package ru.agrachev.feature.productcard.presentation.shape

import androidx.annotation.IntRange
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

internal class StarShape(
    @get:IntRange(from = 3) private val numPoints: Int = DEFAULT_SHAPE_NUM_POINTS,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        val points = calculateStarPoints(
            centerX = size.width / 2,
            centerY = size.height / 2,
            outerRadius = size.minDimension / 2f,
        )
        path.moveTo(points[0].first, points[0].second)
        for (i in 1 until points.size) {
            path.lineTo(points[i].first, points[i].second)
        }
        path.close()
        return Outline.Generic(path)
    }

    private fun calculateStarPoints(
        centerX: Float,
        centerY: Float,
        outerRadius: Float,
    ): List<Pair<Float, Float>> {
        val innerRadius = outerRadius / 2f
        val angleStep = PI / numPoints
        return List(numPoints * 2) {
            val radius = if (it % 2 == 0) outerRadius else innerRadius
            val angle = it * angleStep - PI / 2
            val x = (centerX + radius * cos(angle)).toFloat()
            val y = (centerY + radius * sin(angle)).toFloat()
            x to y
        }
    }
}

internal val FivePointStar = StarShape()

private const val DEFAULT_SHAPE_NUM_POINTS = 5
