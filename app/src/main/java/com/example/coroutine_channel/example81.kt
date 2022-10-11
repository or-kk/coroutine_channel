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
    }

    val job = launch {
        repeat(10) {
            println(channel.receive())
        }
        println("완료")
    }
}