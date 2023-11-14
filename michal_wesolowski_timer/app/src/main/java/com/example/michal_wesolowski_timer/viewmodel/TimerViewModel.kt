package com.example.michal_wesolowski_timer.viewmodel

import android.os.Vibrator
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.michal_wesolowski_timer.model.Timer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {
    private var _isCountdownRunning = false
    private val _timerState = MutableStateFlow(Timer(0, 0))
    val timerState: StateFlow<Timer> = _timerState.asStateFlow()

    fun add_10_minutes() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                minutes = addTime(10, currentState.minutes)
            )
        }
    }

    fun add_1_minute() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                minutes = addTime(1, currentState.minutes)
            )
        }
    }

    fun sub_10_minutes() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                minutes = addTime(-10, currentState.minutes)
            )
        }
    }

    fun sub_1_minute() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                minutes = addTime(-1, currentState.minutes)
            )
        }
    }

    fun add_10_seconds() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                seconds = addTime(10, currentState.seconds)
            )
        }
    }

    fun add_1_second() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                seconds = addTime(1, currentState.seconds)
            )
        }
    }

    fun sub_10_seconds() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                seconds = addTime(-10, currentState.seconds)
            )
        }
    }

    fun sub_1_second() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                seconds = addTime(-1, currentState.seconds)
            )
        }
    }

    fun startCountdown() {
        if (!_isCountdownRunning) {
            _isCountdownRunning = true
            viewModelScope.launch {
                while (_isCountdownRunning && (_timerState.value.minutes > 0 || _timerState.value.seconds > 0)) {
                    delay(1000)
                    _timerState.update { currentState ->
                        currentState.copy(
                            seconds = if(currentState.seconds == 0 && currentState.minutes > 0) 59 else  addTime(-1, currentState.seconds),
                            minutes = if (currentState.seconds == 0) addTime(-1, currentState.minutes) else currentState.minutes
                        )
                    }
                }
                _isCountdownRunning = false
            }
        }
    }

    fun stopCountdown() {
        _isCountdownRunning = false
    }

    fun reset() {
        if(_isCountdownRunning) {
            return
        }
        _timerState.update { currentState ->
            currentState.copy(
                seconds = 0,
                minutes = 0
            )
        }
    }

    private fun addTime(addedTime: Int, currentTime: Int): Int {
        val sum = currentTime + addedTime
        return when {
            sum >= 60 -> 59
            sum < 0 -> 0
            else -> sum
        }
    }
}