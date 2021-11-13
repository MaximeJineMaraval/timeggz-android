package com.jine.timeggz.ui.timer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jine.timeggz.MainViewModel
import com.jine.timeggz.core_style.components.BigButton
import com.jine.timeggz.core_style.components.SmallButton
import com.jine.timeggz.data.models.ReadState

@Composable
internal fun TimerButtons(
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
