package com.example.coroutine_channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

//stream
fun CoroutineScope.produceNumbers() = produce<Int> { // 1, 2, 3, 4, 5, 6, 7, 8
    var x = 1
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        send("${i}!")
    }
}

fun main() = runBlocking<Unit> {
    val numbers = produceNumbers() // 1, 2, 3, 4, 5, 6, 7, 8...
    val stringNumbers = produceStringNumbers(numbers)

    repeat(5) {
        println(stringNumbers.receive())
    }
    println("완료")
}