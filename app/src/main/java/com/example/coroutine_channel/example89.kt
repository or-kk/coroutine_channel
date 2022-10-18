package com.example.coroutine_channel

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun someone(channel: Channel<String>, name: String) {
    for (comment in channel) {
        println("${name}: ${comment}")
        channel.send(comment.drop(1) + comment.first())
        delay(100L)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()

    launch {
        someone(channel, "ralph")
    }

    launch {
        someone(channel, "haley")
    }
    channel.send("abc mart")
    delay(1000L)
    coroutineContext.cancelChildren()
}