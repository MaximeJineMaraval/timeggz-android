package com.jine.timeggz.ui.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jine.timeggz.MainViewModel

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val readState by viewModel.readState.observeAsState(viewModel.readState.value!!)
    val timeLeftInSeconds by viewModel.timeLeftInSeconds.observeAsState(viewModel.timeLeftInSeconds.value!!)
    val timeElapsedInCentiSeconds by viewModel.timeElapsedInCentiSeconds.observeAsState(viewModel.timeElapsedInCentiSeconds.value!!)
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerLabel(readState = readState, timeLeftInSeconds = timeLeftInSeconds)
        Egg(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
            timeElapsedInCentiSeconds = timeElapsedInCentiSeconds
        )
        TimerButtons(readState = readState, viewModel = viewModel)
    }
}
