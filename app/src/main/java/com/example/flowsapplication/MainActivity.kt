package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

const val TAG = "Main Activity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //consumer 1
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect() {
                Log.d(TAG, "onCreate:Consumer One: $it")
            }
        }
        //consumer 2
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect() {
                Log.d(TAG, "onCreate:Consumer Two: $it")
            }
        }
    }

//Hot flow in nature
    private fun producer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>(1)
    GlobalScope.launch {
        val list = listOf<Int>(1, 2, 3, 4, 5)

            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }
}