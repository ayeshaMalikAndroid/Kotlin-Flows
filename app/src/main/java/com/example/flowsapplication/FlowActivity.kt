package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        Log.d("MainActivity1", "onCreate: ")
        //val job =
        //Multiple consumer
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            //consume
            data.collect {
                Log.d("Flows -1 ", it.toString())
            }
        }
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            //consume
            data.collect {
                delay(2500)
                Log.d("Flows ---- 2", it.toString())
            }
        }
//        GlobalScope.launch {
//            delay(3500)
//            job.cancel()
//       }
    }

    private fun producer() = flow() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}