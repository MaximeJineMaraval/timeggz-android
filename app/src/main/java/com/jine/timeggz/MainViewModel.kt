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
            if (value.timeLeftInCentiSeconds % 100 == 0) {
                _timeLeftInSeconds.postValue(value.timeLeftInSeconds)
            }
            if (value.timeElapsedInCentiSeconds % 5 == 0) {
                _timeElapsedInCentiSeconds.postValue(value.timeElapsedInCentiSeconds)
            }
        }

    private val _readState: MutableLiveData<ReadState> = MutableLiveData(timer.readState)
    private val _timeLeftInSeconds: MutableLiveData<Int> =
        MutableLiveData(timer.timeLeftInSeconds)
    private val _timeElapsedInCentiSeconds: MutableLiveData<Int> =
        MutableLiveData(timer.timeElapsedInCentiSeconds)

    val readState: LiveData<ReadState>
        get() = _readState
    val timeLeftInSeconds: LiveData<Int>
        get() = _timeLeftInSeconds
    val timeElapsedInCentiSeconds: LiveData<Int>
        get() = _timeElapsedInCentiSeconds

    fun startTimer() {
        timer = timer.copy(readState = ReadState.STARTED)
        viewModelScope.launchDefault {
            getTimerFlow().collectLatest { timeLeftInCentiSeconds ->
                timer = if (timeLeftInCentiSeconds == 0) {
                    timer.copy(
                        timeLeftInCentiSeconds = timeLeftInCentiSeconds,
                        readState = ReadState.FINISHED
                    )
                } else {
                    timer.copy(timeLeftInCentiSeconds = timeLeftInCentiSeconds)
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
            emit(timer.timeLeftInCentiSeconds)
            while (timer.timeLeftInCentiSeconds > 0) {
                delay(10L)
                if (timer.readState == ReadState.STARTED) {
                    emit(timer.timeLeftInCentiSeconds - 1)
                } else {
                    break
                }
            }
        }
    }

    companion object {
        private const val TIMER_INITIAL_VALUE = 540 * 100
    }
}
