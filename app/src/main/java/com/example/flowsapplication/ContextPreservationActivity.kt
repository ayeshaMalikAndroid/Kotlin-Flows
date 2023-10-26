package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContextPreservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_preservation)
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .map {
                    delay(1000)
                    it * 2
                    Log.d(TAG, "onCreate: Map Thread - ${Thread.currentThread().name}")
                }
                .filter {
                    delay(500)
                    Log.d(TAG, "onCreate:Filter Thread - ${Thread.currentThread().name}")
                    it < 8
                }
                .flowOn(Dispatchers.IO)
                .collect() {
                    Log.d(TAG, "onCreate: Collector Thread - ${Thread.currentThread().name}")
                }
        }
    }


    private fun producer(): Flow<Int> {
        return flow<Int> {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                Log.d(TAG, "producer: Emitter thread - ${Thread.currentThread().name}")
                emit(it)
            }
        }

    }
}