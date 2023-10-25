package com.example.flowsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class ChannelActivity : AppCompatActivity() {
    //To create a channel, you can use the Channel factory function
    private val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)
        producer()
        consumer()
    }
    //Produce Values:
    private fun producer() {
        CoroutineScope(Dispatchers.Main).launch {
            //use the send function to send values to the channel.
            channel.send(1)
            channel.send(2)
            channel.send(3)


        }
    }

    //receive data though channel
    //Consume Values:

    private fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            //To receive values from the channel, you can use the receive function.
            // It's a suspending function, so you should call it
            // from within a coroutine or a suspend function

            Log.d("Consumer", channel.receive().toString())
            Log.d("Consumer", channel.receive().toString())
            Log.d("Consumer", channel.receive().toString())
        }
    }
}