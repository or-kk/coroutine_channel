package com.example.coroutine_channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.filterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    // ProducerScope = coroutine scope
    for (i in numbers) {
        if (i % 2 == 1) {
            send("${i}!")
        }
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers() // send x
    val oddNumbers = filterOdd(numbers) // send x

    repeat(10) {
        println(oddNumbers.receive())
    }
    println("완료")
}

