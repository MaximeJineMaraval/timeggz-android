package com.jine.timeggz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.jine.timeggz.core_style.themes.TimeggzTheme
import com.jine.timeggz.ui.screens.TimerScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            TimeggzTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TimerScreen(viewModel = mainViewModel)
                }
            }
        }
    }
}
