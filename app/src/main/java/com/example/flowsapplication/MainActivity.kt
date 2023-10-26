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
        GlobalScope.launch(Dispatchers.Main) {
           val result = producer()
            Log.d(TAG, "onCreate: ${result.value}")
//            delay(5000)
//            result.collect() {
//                Log.d(TAG, "onCreate:Item -  $it")
//            }
        }
    }



    private fun producer(): StateFlow<Int> {
        val mutableState = MutableStateFlow(10)
        GlobalScope.launch {
            delay(1000)
            mutableState.emit(20)
            delay(1000)
            mutableState.emit(30)
        }

        return mutableState
    }

//    private fun producer(): Flow<Int> {
//        val mutableState = MutableStateFlow(10)
//        GlobalScope.launch {
//            delay(1000)
//            mutableState.emit(20)
//            delay(1000)
//            mutableState.emit(30)
//        }
//
//        return mutableState
//    }

//    private fun producer(): Flow<Int> {
//        val mutableSharedFlow = MutableSharedFlow<Int>(1)
//        GlobalScope.launch {
//            val list = listOf<Int>(1, 2, 3, 4, 5)
//
//            list.forEach {
//                mutableSharedFlow.emit(it)
//                Log.d(TAG, "producer: Emitting - $it")
//                delay(1000)
//            }
//        }
//        return mutableSharedFlow
//    }
}