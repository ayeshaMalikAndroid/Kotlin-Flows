package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class FlowOperatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_operator)
        //Kotlin Flow Operators - Terminal + Map, Filter Buffer Operators
        GlobalScope.launch(Dispatchers.Main) {
            val time = measureTimeMillis {
                producer()
                    .buffer(5)
                    .collect() {
                        delay(1500)
                        Log.d(TAG, "onCreate: $it")
                    }

            }
            Log.d(TAG, time.toString())
            //non terminal operator
//            producer()
//                .map {
//                    it * 2
//                }
//                .filter {
//                    it < 8
//                }
//
//
//                .collect(){
//                Log.d(TAG, "onCreate: $it")
//            }


            //terminal operator
            //  var result =   producer().first()
//            var result =   producer().toList()
//            Log.d(TAG, "onCreate: $result")
//                .onStart {
//                    emit(-1)
//                    Log.d(TAG, "onCreate:Starting  ")
//                }
//                .onCompletion {
//                    emit(6)
//                    Log.d(TAG, "onCreate:Completed ")
//
//                }
//                .onEach {
//                    Log.d(TAG, "About to Emit $it")
//                }
//                .collect {
//                    Log.d(TAG, " Collect:  $it")
//                }
        }
    }


    private fun producer(): Flow<Int> {
        return flow<Int> {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                emit(it)
            }
        }

    }
}
