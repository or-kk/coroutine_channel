package com.example.coroutine_channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers2() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.processNumber(id: Int, channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${id}가 ${it}을 받았습니다.")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers2()
    repeat(5) {
        processNumber(it, producer)
    }
    delay(1000L)
    producer.cancel()
}