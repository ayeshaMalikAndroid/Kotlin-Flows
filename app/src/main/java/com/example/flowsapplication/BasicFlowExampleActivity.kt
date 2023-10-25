package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BasicFlowExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_flow_example)
        CoroutineScope(Dispatchers.Main).launch {
            getUserNames().forEach { i ->
                Log.d("TAG", i)
            }
        }
    }
        private suspend fun getUserNames(): List<String> {
            val list = mutableListOf<String>()
            list.add(getUser(1))
            list.add(getUser(2))
            list.add(getUser(3))
            return list
        }

        private suspend fun getUser(id: Int): String {
            delay(100) // Assume Network Call
            return "User$id"
        }

}
