package com.example.chapter3

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val onToTen = produce {
        for (x in 1..10) {
            channel.send(x)
        }
    }

    onToTen.consumeEach {
        println(it)
    }
    println("완료")
}