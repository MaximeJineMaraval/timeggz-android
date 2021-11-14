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
            _timeLeftInSeconds.postValue(value.timeLeftInSeconds)
            _timeElapsedInSeconds.postValue(value.timeElapsedInSeconds)
        }

    private val _readState: MutableLiveData<ReadState> = MutableLiveData(timer.readState)
    private val _timeLeftInSeconds: MutableLiveData<Int> = MutableLiveData(timer.timeLeftInSeconds)
    private val _timeElapsedInSeconds: MutableLiveData<Int> =
        MutableLiveData(timer.timeElapsedInSeconds)

    val readState: LiveData<ReadState>
        get() = _readState
    val timeLeftInSeconds: LiveData<Int>
        get() = _timeLeftInSeconds
    val timeElapsedInSeconds: LiveData<Int>
        get() = _timeElapsedInSeconds

    fun startTimer() {
        timer = timer.copy(readState = ReadState.STARTED)
        viewModelScope.launchDefault {
            getTimerFlow().collectLatest { timeLeftInSeconds ->
                timer = if (timeLeftInSeconds == 0) {
                    timer.copy(
                        timeLeftInSeconds = timeLeftInSeconds,
                        readState = ReadState.FINISHED
                    )
                } else {
                    timer.copy(timeLeftInSeconds = timeLeftInSeconds)
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
        return Timer(TIMER_INITIAL_VALUE, ReadState.INITIAL, TIMER_INITIAL_VALUE)
    }

    private fun getTimerFlow(): Flow<Int> {
        return flow {
            emit(timer.timeLeftInSeconds)
            while (timer.timeLeftInSeconds > 0) {
                delay(1000L)
                if (timer.readState == ReadState.STARTED) {
                    emit(timer.timeLeftInSeconds - 1)
                } else {
                    break
                }
            }
        }
    }

    companion object {
        private const val TIMER_INITIAL_VALUE = 540
    }
}
