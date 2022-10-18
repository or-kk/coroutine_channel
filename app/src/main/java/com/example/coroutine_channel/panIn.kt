package com.example.coroutine_channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun CoroutineScope.produceNumbers3(channel: SendChannel<Int>, from: Int, interval: Long) = produce<Int> {
    var x = from
    while (true) {
        channel.send(x)
        x+=2
        delay(interval)
    }
}

fun CoroutineScope.processNumber(channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${it}를 받았습니다.")
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>() // Channel = Receive Channel (Receive) + send Channel (Send)
    launch {
        produceNumbers3(channel, 1, 100L)
    }
    launch {
        produceNumbers3(channel, 2, 150L)
    }
    processNumber(channel)
    delay(1000L)
    coroutineContext.cancelChildren()
}