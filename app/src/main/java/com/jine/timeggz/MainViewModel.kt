package com.jine.timeggz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jine.timeggz.core_extensions.launchDefault
import com.jine.timeggz.data.models.ReadState
import com.jine.timeggz.data.models.Timer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var timer: Timer = getInitialTimer()
        set(value) {
            field = value
            _readState.postValue(value.readState)
            _timeInSeconds.postValue(value.timeInSeconds)
        }

    private val _readState: MutableLiveData<ReadState> = MutableLiveData(timer.readState)
    private val _timeInSeconds: MutableLiveData<Int> = MutableLiveData(timer.timeInSeconds)

    val readState: LiveData<ReadState>
        get() = _readState
    val timeInSeconds: LiveData<Int>
        get() = _timeInSeconds

    fun startTimer() {
        timer = timer.copy(readState = ReadState.STARTED)
        viewModelScope.launchDefault {
            getTimerFlow().collectLatest { timeInSeconds ->
                timer = if (timeInSeconds == 0) {
                    timer.copy(timeInSeconds = timeInSeconds, readState = ReadState.FINISHED)
                } else {
                    timer.copy(timeInSeconds = timeInSeconds)
                }
            }
        }
    }

    fun resetTimer() {
        timer = getInitialTimer()
    }

    fun pauseTimer() {
        timer = timer.copy(readState = ReadState.PAUSED)
    }

    private fun getInitialTimer(): Timer {
        return Timer(ReadState.INITIAL, 180)
    }

    private fun getTimerFlow(): Flow<Int> {
        return flow {
            emit(timer.timeInSeconds)
            while (timer.timeInSeconds > 0) {
                delay(1000L)
                if (timer.readState == ReadState.STARTED) {
                    emit(timer.timeInSeconds - 1)
                } else {
                    break
                }
            }
        }
    }
}
