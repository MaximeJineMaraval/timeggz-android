package com.jine.timeggz.ui.timer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.jine.timeggz.core_style.themes.TimeggzTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun Egg(modifier: Modifier = Modifier, timeElapsedInSeconds: Int) {
    val eggWhiteAlphaState = getEggWhiteAlphaState(timeElapsedInSeconds = timeElapsedInSeconds)
    val eggYolkColor = MaterialTheme.colorScheme.primary
    Canvas(
        modifier
            .fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val eggHeight = size.minDimension / 2
        drawEgg(
            color = Color.White,
            eggHeight = eggHeight,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            alpha = eggWhiteAlphaState.value
        )
        drawCircle(
            color = eggYolkColor,
            center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) + (eggHeight / 4)),
            radius = eggHeight / 2.5f
        )
    }
}

/**
 * The egg white start from a transparent state and will be totally opaque after 3 minutes
 */
@Composable
private fun getEggWhiteAlphaState(timeElapsedInSeconds: Int): State<Float> {
    val startedAlpha = 0.25f
    val targetAlpha = 1.0f
    val animationDuration = 180
    val alphaStep = (targetAlpha - startedAlpha) / animationDuration
    return animateFloatAsState(
        targetValue = if (timeElapsedInSeconds > animationDuration) {
            targetAlpha
        } else {
            startedAlpha + (alphaStep * timeElapsedInSeconds)
        }
    )
}

/**
 * The egg path algorithm has been inspired by this article
 * https://nyjp07.com/index_egg_by_Itou_E.html
 */
private fun DrawScope.drawEgg(color: Color, alpha: Float = 1.0f, eggHeight: Float, center: Offset) {
    val path = Path()
    var index = -PI

    fun getX(): Float {
        return ((eggHeight * 0.78 * cos(index * 0.25) * sin(index)) + center.x).toFloat()
    }

    fun getY(): Float {
        return ((eggHeight * cos(index)) + center.y).toFloat()
    }

    fun incrementIndex() {
        index += (PI / 100f)
    }

    // Define the path
    path.moveTo(getX(), getY())
    incrementIndex()
    while (index <= PI) {
        path.lineTo(getX(), getY())
        incrementIndex()
    }

    // Draw the path
    drawPath(
        color = color,
        alpha = alpha,
        path = path
    )
}

@Preview
@Composable
fun EggPreview() {
    TimeggzTheme {
        Egg(timeElapsedInSeconds = 540)
    }
}
