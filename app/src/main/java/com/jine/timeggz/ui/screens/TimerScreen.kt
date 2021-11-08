package com.jine.timeggz.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jine.timeggz.MainViewModel
import com.jine.timeggz.core_extensions.fromSecondsToMinuteAndSeconds
import com.jine.timeggz.core_style.components.BigButton
import com.jine.timeggz.core_style.components.SmallButton
import com.jine.timeggz.data.models.ReadState

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val readState by viewModel.readState.observeAsState(viewModel.readState.value!!)
    val timeInSeconds by viewModel.timeInSeconds.observeAsState(viewModel.timeInSeconds.value!!)
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerText(readState = readState, timeInSeconds = timeInSeconds)
        Egg(
            Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically)
        )
        TimerButtons(readState = readState, viewModel = viewModel)
    }
}

@Composable
private fun TimerText(
    modifier: Modifier = Modifier,
    readState: ReadState,
    timeInSeconds: Int
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
        text = timeInSeconds.fromSecondsToMinuteAndSeconds()
    )
}

@Composable
private fun Egg(modifier: Modifier) {
    Surface(modifier.fillMaxSize(), color = Color.Gray) {
    }
}

@Composable
private fun TimerButtons(
    modifier: Modifier = Modifier,
    readState: ReadState,
    viewModel: MainViewModel
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedSideViews(readState = readState) {
            SmallButton(
                icon = Icons.Filled.Refresh
            ) { viewModel.resetTimer() }
        }
        PlayButton(readState = readState, viewModel = viewModel)
        AnimatedSideViews(readState = readState) {
            Surface(modifier = Modifier.size(56.dp)) {}
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedSideViews(
    readState: ReadState,
    viewToAnimate: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = readState == ReadState.STARTED || readState == ReadState.PAUSED,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        viewToAnimate()
    }
}

@Composable
private fun PlayButton(
    modifier: Modifier = Modifier,
    readState: ReadState,
    viewModel: MainViewModel
) {
    val playButtonModifier = modifier.padding(horizontal = 16.dp)
    when (readState) {
        ReadState.STARTED -> BigButton(
            modifier = playButtonModifier,
            icon = Icons.Filled.Pause
        ) { viewModel.pauseTimer() }
        ReadState.PAUSED,
        ReadState.INITIAL -> BigButton(
            modifier = playButtonModifier,
            icon = Icons.Filled.PlayArrow
        ) { viewModel.startTimer() }
        ReadState.FINISHED -> BigButton(
            modifier = playButtonModifier,
            icon = Icons.Filled.Stop
        ) { viewModel.resetTimer() }
    }
}
