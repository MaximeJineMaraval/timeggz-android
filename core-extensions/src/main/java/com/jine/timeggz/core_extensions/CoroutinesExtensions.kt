package com.jine.timeggz.core_extensions

import kotlinx.coroutines.*

fun CoroutineScope.launchDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.Default, start, block)
}

fun CoroutineScope.launchIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.IO, start, block)
}

fun CoroutineScope.launchMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.Main, start, block)
}
