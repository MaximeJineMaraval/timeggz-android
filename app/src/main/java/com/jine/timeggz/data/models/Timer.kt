package com.jine.timeggz.data.models

data class Timer(
    val initialTimeInSeconds: Int,
    var readState: ReadState,
    var timeLeftInSeconds: Int
) {

    val timeElapsedInSeconds: Int
        get() = initialTimeInSeconds - timeLeftInSeconds
}
