package com.jine.timeggz.data.models

data class Timer(
    val initialTimeInCentiSeconds: Int,
    var readState: ReadState,
    var timeLeftInCentiSeconds: Int
) {

    val timeElapsedInCentiSeconds: Int
        get() = initialTimeInCentiSeconds - timeLeftInCentiSeconds

    val timeLeftInSeconds: Int
        get() = timeLeftInCentiSeconds / 100
}
