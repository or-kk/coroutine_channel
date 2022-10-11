package com.example.chapter3

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>()

    launch {
        for (x in 1..10) {
            channel.send(x) // suspension point
        }
    }

    repeat(10) {
        println(channel.receive()) // suspension point
    }
    println("완료")
}