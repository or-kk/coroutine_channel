package com.example.chapter3

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x)
        }
        channel.close()
    }

    for (x in channel) {
        println(x)
    }

    println("완료")
}