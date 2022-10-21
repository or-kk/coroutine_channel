package com.example.coroutine_channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun CoroutineScope.sayFast() = produce<String> {
    while (true) {
        delay(100L)
        send("fast")
    }
}

fun CoroutineScope.sayCampus() = produce<String> {
    while (true) {
        delay(150L)
        send("slow")
    }
}

fun main() = runBlocking<Unit> {
    val fasts = sayFast()
    val campuses = sayCampus()
    repeat (5) { // 5 times select
        select<Unit> { // 먼저 끝내는 애만 듣는다.
            fasts.onReceive {
                println("fast: $it")
            }
            campuses.onReceive {
                println("campus: $it")
            }
        }
    }
    coroutineContext.cancelChildren()
}