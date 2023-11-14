package com.example.michal_wesolowski_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.michal_wesolowski_timer.viewmodel.TimerViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _add10Minutes: Button
    private lateinit var _add1Minute: Button
    private lateinit var _add10Seconds: Button
    private lateinit var _add1Second: Button
    private lateinit var _sub10Minutes: Button
    private lateinit var _sub1Minute: Button
    private lateinit var _sub10Seconds: Button
    private lateinit var _sub1Second: Button
    private lateinit var _minuteDecimal: TextView
    private lateinit var _minuteDigit: TextView
    private lateinit var _secondDecimal: TextView
    private lateinit var _secondDigit: TextView
    private lateinit var _start: Button
    private lateinit var _stop: Button
    private lateinit var _reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _add10Minutes = findViewById(R.id.add_10_minutes)
        _add1Minute = findViewById(R.id.add_1_minute)
        _add10Seconds = findViewById(R.id.add_10_seconds)
        _add1Second = findViewById(R.id.add_1_second)
        _sub10Minutes = findViewById(R.id.sub_10_minutes)
        _sub1Minute = findViewById(R.id.sub_1_minute)
        _sub10Seconds = findViewById(R.id.sub_10_seconds)
        _sub1Second = findViewById(R.id.sub_1_second)
        _minuteDecimal = findViewById(R.id.minute_decimal)
        _minuteDigit = findViewById(R.id.minute_digit)
        _secondDecimal = findViewById(R.id.second_decimal)
        _secondDigit = findViewById(R.id.second_digit)
        _start = findViewById(R.id.start_button)
        _stop = findViewById(R.id.stop_button)
        _reset = findViewById(R.id.reset_button)


        val viewModel: TimerViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.timerState.collect {timer->
                    _minuteDecimal.text = (timer.minutes / 10).toString()
                    _minuteDigit.text = (timer.minutes % 10).toString()
                    _secondDecimal.text = (timer.seconds / 10).toString()
                    _secondDigit.text = (timer.seconds % 10).toString()
                }
            }
        }

        _add10Minutes.setOnClickListener {
            viewModel.add_10_minutes()
        }
        _add1Minute.setOnClickListener {
            viewModel.add_1_minute()
        }
        _add10Seconds.setOnClickListener {
            viewModel.add_10_seconds()
        }
        _add1Second.setOnClickListener {
            viewModel.add_1_second()
        }
        _sub10Minutes.setOnClickListener {
            viewModel.sub_10_minutes()
        }
        _sub1Minute.setOnClickListener {
            viewModel.sub_1_minute()
        }
        _sub10Seconds.setOnClickListener {
            viewModel.sub_10_seconds()
        }
        _sub1Second.setOnClickListener {
            viewModel.sub_1_second()
        }

        _start.setOnClickListener {
            viewModel.startCountdown()
        }
        _stop.setOnClickListener {
            viewModel.stopCountdown()
        }
        _reset.setOnClickListener {
            viewModel.reset()
        }

    }
}