package com.example.coroutine_channel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val channel = Channel<Int>(2, BufferOverflow.DROP_OLDEST)

    launch {
        for (x in 1..20) {
            println("${x} 전송중")
            channel.send(x)
        }
        channel.close()
    }

    delay(500)

    for (x in channel) {
        println("${x} 수신")
        delay(100L)
    }

    println("완료")
}