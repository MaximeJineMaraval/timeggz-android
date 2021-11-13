package com.jine.timeggz.ui.timer

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jine.timeggz.core_extensions.fromSecondsToMinuteAndSeconds
import com.jine.timeggz.data.models.ReadState

@Composable
internal fun TimerLabel(
    modifier: Modifier = Modifier,
    readState: ReadState,
    timeLeftInSeconds: Int
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 150,
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val textAlpha = if (readState == ReadState.PAUSED || readState == ReadState.FINISHED) {
        animationProgress
    } else {
        1f
    }
    Text(
        modifier = modifier
            .padding(16.dp)
            .alpha(textAlpha),
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center,
        text = timeLeftInSeconds.fromSecondsToMinuteAndSeconds()
    )
}
